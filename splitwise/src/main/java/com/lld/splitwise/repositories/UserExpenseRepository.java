package com.lld.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lld.splitwise.models.Expense;
import com.lld.splitwise.models.User;
import com.lld.splitwise.models.UserExpense;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
	
	List<UserExpense> findAllByUser(User user);
	
	List<UserExpense> findAllByExpense(Expense expense);

}
