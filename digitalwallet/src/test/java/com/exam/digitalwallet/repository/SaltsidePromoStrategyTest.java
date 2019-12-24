package com.exam.digitalwallet.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.exam.digitalwallet.model.Account;

class SaltsidePromoStrategyTest {

	@InjectMocks
	SaltsidePromoStrategy saltsidePromoStrategy = new SaltsidePromoStrategy();
	
	@Test
	void testApplyPromotion() {
		Account account =new Account("1", "digital-wallet",100, true);
		assertEquals(30, saltsidePromoStrategy.applyPromotion(Optional.of(account), 300));
	}
	
	@Test
	void testApplyPromotion_thenPromotionmorethan50() {
		Account account =new Account("1", "digital-wallet",100, true);
		assertEquals(50, saltsidePromoStrategy.applyPromotion(Optional.of(account), 600));
	}

}
