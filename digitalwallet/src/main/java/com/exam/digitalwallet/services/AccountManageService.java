package com.exam.digitalwallet.services;

import java.util.List;
import java.util.Optional;

import com.exam.digitalwallet.model.Account;
import com.exam.digitalwallet.model.RechargeRequest;

public interface AccountManageService {

	 Account saveAccount(Account account);
	 Account use(String id,int useAmount);
	 Account useBalance(String id,int amount);
	 int checkBalance(String id);
	 public Account addBalance(String id,RechargeRequest recharge);
	
	 
	 
	
}
