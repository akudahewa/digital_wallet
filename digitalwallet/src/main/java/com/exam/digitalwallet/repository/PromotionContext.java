package com.exam.digitalwallet.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.exam.digitalwallet.controller.AccountController;
import com.exam.digitalwallet.model.Account;

@Component
public class PromotionContext {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private WelcomePromoStrategy welcomePromotionStrategy;
	@Autowired
	private SaltsidePromoStrategy saltsidePromotionStrategy;
	
	private Map<String,PromotionStrategy> promotionStrategyMap =new HashMap<>() ;
	@Autowired
    public void setMap() {
		promotionStrategyMap.put("WELCOME", welcomePromotionStrategy);
		promotionStrategyMap.put("SALTSIDE", saltsidePromotionStrategy);
    }
	
	public int executePromotion(String promoCode,Optional<Account> account,int rechargeAmount) {
		return promotionStrategyMap.get(promoCode).applyPromotion(account,rechargeAmount);
	}	
}
