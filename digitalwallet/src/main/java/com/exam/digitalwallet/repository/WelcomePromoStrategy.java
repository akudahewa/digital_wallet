package com.exam.digitalwallet.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.exam.digitalwallet.model.Account;

@Repository
public class WelcomePromoStrategy implements PromotionStrategy {

	@Override
	public int applyPromotion(Optional<Account> account,int rechargeAmount) {
		int promoBalance=0;
		if(!account.get().isRecharged()) {
				if(rechargeAmount >50) {
					promoBalance =20;
				}
		}
		return promoBalance;
	}

}
