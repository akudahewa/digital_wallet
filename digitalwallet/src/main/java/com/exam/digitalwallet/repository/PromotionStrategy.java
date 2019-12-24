package com.exam.digitalwallet.repository;

import java.util.Optional;

import com.exam.digitalwallet.model.Account;

public interface PromotionStrategy {
	
	
	public int applyPromotion(Optional<Account> account,int rechargeAmount);
	

}
