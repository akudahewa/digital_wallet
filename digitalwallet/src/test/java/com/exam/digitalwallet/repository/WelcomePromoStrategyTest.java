package com.exam.digitalwallet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.exam.digitalwallet.model.Account;

class WelcomePromoStrategyTest {

	@InjectMocks
	WelcomePromoStrategy welcomePromoStrategy = new WelcomePromoStrategy();
	
	@Test
	void testApplyPromotion() {
		Account account  =new Account("1","digital-wallet-1",0, false);
		assertEquals(20, welcomePromoStrategy.applyPromotion(Optional.of(account), 100));
	}
	@Test
	void testApplyPromotion_thenRechargeLessthen50() {
		Account account =new Account("2", "digital-wallet-2",0, false);
		assertEquals(0, welcomePromoStrategy.applyPromotion(Optional.of(account),
				25));
	}
	@Test
	void testApplyPromotion_thenAlreadyRecharge() {
		Account account =new Account("2", "digital-wallet-2",100, true);
		assertEquals(0, welcomePromoStrategy.applyPromotion(Optional.of(account),
				200));
	}

}
