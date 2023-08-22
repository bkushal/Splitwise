package com.lld.splitwise.dtos;


//Settling up at individual level
public class SettleUpRequestDto {

	private Long userId;

	public SettleUpRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SettleUpRequestDto(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
