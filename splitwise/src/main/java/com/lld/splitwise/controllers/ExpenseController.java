package com.lld.splitwise.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lld.splitwise.dtos.SettleUpRequestDto;
import com.lld.splitwise.dtos.SettleUpResponseDto;
import com.lld.splitwise.exceptions.InvalidArgumentsException;
import com.lld.splitwise.models.Expense;
import com.lld.splitwise.services.ExpenseService;

@Controller
public class ExpenseController {
	private ExpenseService expenseService;
	
	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
	//Settling up at individual level
	public SettleUpResponseDto settleUp(SettleUpRequestDto request) {
		
		Long userId = request.getUserId();
		List<Expense> transactions = null;
		try {
			transactions = this.expenseService.settleUp(userId);
		}catch(InvalidArgumentsException e) {
			
		}
		
		SettleUpResponseDto response = new SettleUpResponseDto();
		for(Expense expense : transactions) {
			List<Object> data = new ArrayList<>();
			data.add(expense.getUser().getId());
			data.add(expense.getUser().getName());
			data.add(expense.getAmount());
			response.getTransactions().add(data);
		}
		
		return response;
	}

}
