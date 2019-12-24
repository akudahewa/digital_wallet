package com.exam.digitalwallet.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.InsufficientResourcesException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

import com.exam.digitalwallet.model.Account;
import com.exam.digitalwallet.model.RechargeRequest;
import com.exam.digitalwallet.repository.AccountRepository;
import com.exam.digitalwallet.repository.PromotionContext;

@SpringBootTest
class AccountManageServiceImplTest {
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private PromotionContext PromotionContext;
	
	@InjectMocks
	private AccountManageService accountManageService = new AccountManageServiceImpl();
	 
	@Before
	 public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	void testSaveAccount() {
		Account account3= new Account("3", "wallert-3", 30, true);
		accountManageService.saveAccount(account3);
		verify(accountRepository,times(1)).save(account3);
	}

	@Test
	void testCheckBalance() {
		Account account= new Account("4", "wallert-3", 30, true);
		when(accountRepository.findById("1")).thenReturn(Optional.of(account));	
		int acc= accountManageService.checkBalance("1");
		assertEquals(30,acc );
	}
	
	@Test
	void testUseBalance() {
		Account account= new Account("4", "wallert-3", 30, true);
		Account updatedAccount = new Account("5", "wallert-3", 20, true);
		when(accountRepository.findById("1")).thenReturn(Optional.of(account));
		when(accountRepository.save(account)).thenReturn(account);
		Account acc= accountManageService.useBalance("1", 10);
		assertEquals(20,acc.getBalance());
	}
	@Test
	void testAddAccountBalance() {
		Account account2= new Account("2", "wallert-3", 0, false);
		//Account updatedAccount = new Account("2", "wallert-3", 86, true);
		when(accountRepository.findById("2")).thenReturn(Optional.of(account2));
		
		when(PromotionContext.executePromotion("WELCOME", Optional.of(account2), 60))
		.thenReturn(20);
		when(PromotionContext.executePromotion("SALTSIDE", Optional.of(account2), 60))
		.thenReturn(6);
		when(accountRepository.save(account2)).thenReturn(account2);
		String[] codes = {"WELCOME","SALTSIDE"};
		RechargeRequest recharge = new RechargeRequest();
		recharge.setRechargeAmount(60);
		recharge.setPromocodes(codes);
		Account acc= accountManageService.addAccountBalance("2", recharge);
		assertEquals(86, acc.getBalance());
	}

}
