package com.lld.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class UserExpense extends BaseModel {
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Expense expense;
	
	@Enumerated(EnumType.ORDINAL)
	private UserExpenseType userExpenseType;
	private int amount;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public UserExpenseType getUserExpenseType() {
		return userExpenseType;
	}
	public void setUserExpenseType(UserExpenseType userExpenseType) {
		this.userExpenseType = userExpenseType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
