package com.exam.digitalwallet.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PromotionalBalance {
	@Min(0)
	private int balance;
	private LocalDateTime startDate = LocalDateTime.now();

	public int getBalance() {
		return balance;
	}
	public PromotionalBalance() {};
	public PromotionalBalance(int balance, LocalDateTime startDate) {
		super();
		this.balance = balance;
		this.startDate = startDate;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}	
}
