package com.exam.digitalwallet.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	
	@Id
	private String id;
	private String name;
	@Min(0)
	private int balance;
	private boolean isRecharged;
	private PromotionalBalance promotionalBalance;
	
	public Account(String id, String name, @Min(0) int balance, boolean isRecharged) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.isRecharged = isRecharged;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}	
	public boolean isRecharged() {
		return isRecharged;
	}
	public void setRecharged(boolean isRecharged) {
		this.isRecharged = isRecharged;
	}
	public PromotionalBalance getPromotionalBalance() {
		return promotionalBalance;
	}
	public void setPromotionalBalance(PromotionalBalance promotionalBalance) {
		this.promotionalBalance = promotionalBalance;
	}
}
