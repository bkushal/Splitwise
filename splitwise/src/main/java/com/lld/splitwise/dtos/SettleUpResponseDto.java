package com.lld.splitwise.dtos;

import java.util.List;

//Settling up at individual level
public class SettleUpResponseDto {
	
	List<List<Object>> transactions;

	public SettleUpResponseDto(List<List<Object>> transactions) {
		super();
		this.transactions = transactions;
	}

	public SettleUpResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<List<Object>> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<List<Object>> transactions) {
		this.transactions = transactions;
	}

}
