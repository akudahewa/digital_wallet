package com.exam.digitalwallet.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.exam.digitalwallet.model.Account;

@Repository
public class SaltsidePromoStrategy implements PromotionStrategy{

	@Override
	public int applyPromotion(Optional<Account> account,int rechargeAmount) {
		int promoBalance=0;
		promoBalance =rechargeAmount *10/100 >50?50:rechargeAmount *10/100;
		return promoBalance;
	}

}
