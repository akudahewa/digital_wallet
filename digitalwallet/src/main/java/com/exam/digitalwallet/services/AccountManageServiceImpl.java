package com.exam.digitalwallet.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.digitalwallet.controller.AccountController;
import com.exam.digitalwallet.exception.InsuffientBalanceException;
import com.exam.digitalwallet.exception.RecordNotFoundException;
import com.exam.digitalwallet.model.Account;
import com.exam.digitalwallet.model.RechargeRequest;
import com.exam.digitalwallet.repository.AccountRepository;
import com.exam.digitalwallet.repository.PromotionContext;

@Service
public class AccountManageServiceImpl implements AccountManageService {

	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PromotionContext promotionContext;
	
	@Override
	public Account saveAccount(Account account) {
		logger.info("Account object going to be save");
		return accountRepository.save(account);
	}

	@Override
	public int checkBalance(String id) {
		 Optional<Account> account = accountRepository.findById(id);
		 if(!account.isPresent()) {
			 throw new RecordNotFoundException("Record not found");
		 }
		return account.get().getBalance();
	}
	@Override
	public Account useBalance(String id, int amount) {
		Optional<Account> account = accountRepository.findById(id);
		 if(!account.isPresent()) {
			 throw new RecordNotFoundException("Record not found");
		 }
		int newBalance = account.get().getBalance()-amount;
		if(newBalance <0) {
			throw new  InsuffientBalanceException("Account does not have enought balance");
		}
		account.get().setBalance(newBalance);
		return saveOrUpdateAccount(account.get());
	}
	
	@Override
	public Account addAccountBalance(String id, RechargeRequest recharge) {
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isPresent()) {
			 throw new RecordNotFoundException("Record not found");
		 }
		String[] promoCodes = recharge.getPromocodes();
		int promoBalance = 0;
		for (String code : promoCodes) {
			promoBalance += promotionContext.executePromotion(code,account,recharge.getRechargeAmount());
		}
		int totalBalance = account.get().getBalance()+promoBalance+recharge.getRechargeAmount();
		account.get().setBalance(totalBalance);
		account.get().setRecharged(true);
//		Account genAccount = accountRepository.saveOr(account.get());
		return saveOrUpdateAccount(account.get());
	}

	@Override
	public Account use(String id,int useAmount) {
		Optional<Account> account = accountRepository.findById(id);
		 if(!account.isPresent()) {
			 throw new RecordNotFoundException("Record not found");
		 }
		int newBalance = account.get().getBalance()-useAmount;
		if(newBalance <0) {
			throw new  InsuffientBalanceException("Account does not have enought balance");
		}
		account.get().setBalance(newBalance);
		return saveOrUpdateAccount(account.get());
	}
	
	private Account saveOrUpdateAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account addBalance(String id, RechargeRequest recharge) {
		Optional<Account> account = accountRepository.findById(id);
		String[] promoCodes = recharge.getPromocodes();
		int promoBalance = 0;
		for (String code : promoCodes) {
			promoBalance += promotionContext.executePromotion(code,account,recharge.getRechargeAmount());
		}
		int totalBalance = account.get().getBalance()+promoBalance+recharge.getRechargeAmount();
		account.get().setBalance(totalBalance);
		account.get().setRecharged(true);
		Account genAccount = accountRepository.save(account.get());
		return genAccount;
	}

	

	
}
