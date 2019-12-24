package com.exam.digitalwallet.model;

public class RechargeRequest {
	private int rechargeAmount;
	private String[] promocodes;
	
	public int getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(int rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String[] getPromocodes() {
		return promocodes;
	}
	public void setPromocodes(String[] promocodes) {
		this.promocodes = promocodes;
	}
	
}
