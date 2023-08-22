package com.lld.splitwise.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lld.splitwise.exceptions.InvalidArgumentsException;
import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.User;
import com.lld.splitwise.models.UserExpense;
import com.lld.splitwise.models.UserExpenseType;
import com.lld.splitwise.repositories.UserExpenseRepository;
import com.lld.splitwise.repositories.UserRepository;

@Service
public class ExpenseService {
	
	private UserExpenseRepository userExpRepo;
	private UserRepository userRepo;
	
	@Autowired
	public ExpenseService(UserExpenseRepository repo, UserRepository userRepo) {
		this.userExpRepo = repo;
		this.userRepo = userRepo;
	}
	
	//Settling up at individual level
	public List<Expense> settleUp(Long userId) throws InvalidArgumentsException {
		
		Map<User, Integer> map = new HashMap<>();
		
		Optional<User> userOptional = this.userRepo.findById(userId);
		if(userOptional.isEmpty())
			throw new InvalidArgumentsException("Invalid user id sent");
		
		User user = userOptional.get();
		
		//Logic when I had paid the amount
		List<UserExpense> iHadPaid = this.userExpRepo.findAllByUser(user);
		for(UserExpense userExp : iHadPaid) {
			if(userExp.getUserExpenseType().equals(UserExpenseType.PAID)) {
				List<UserExpense> hadToPay = this.userExpRepo.findAllByExpense(userExp.getExpense());
				for(UserExpense exp : hadToPay) {
					if(! exp.getUserExpenseType().equals(UserExpenseType.HAD_TO_PAY))
						continue;
					User otherUser = exp.getUser();
					if(map.containsKey(otherUser))
						map.replace(otherUser, map.get(otherUser)+exp.getAmount());
					else
						map.put(otherUser, exp.getAmount());
					
				}
			}
		}
		
		//Logic when I had paid the amount
		List<UserExpense> iHadToPay = this.userExpRepo.findAllByUser(user);
		for(UserExpense userExp : iHadToPay) {
			if(userExp.getUserExpenseType().equals(UserExpenseType.HAD_TO_PAY)) {
				List<UserExpense> hadToPay = this.userExpRepo.findAllByExpense(userExp.getExpense());
				for(UserExpense exp : hadToPay) {
					if(! exp.getUserExpenseType().equals(UserExpenseType.PAID))
						continue;
					User otherUser = exp.getUser();
					if(map.containsKey(otherUser))
						map.replace(otherUser, map.get(otherUser)-exp.getAmount());   //subtract, meaning I owe money
					else
						map.put(otherUser, 0-exp.getAmount());    //negative to display, I owe money
					
				}
			}
		}		
		
		List<Expense> transactions = new ArrayList<>();
		for (Map.Entry<User,Integer> entry : map.entrySet()) {
			Expense exp = new Expense();
			exp.setUser(entry.getKey());
			exp.setAmount(entry.getValue());
			transactions.add(exp);
		}
            
		return transactions;
	}

}
