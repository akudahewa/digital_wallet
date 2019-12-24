package com.exam.digitalwallet.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.exam.digitalwallet.model.Account;
import com.exam.digitalwallet.model.RechargeRequest;
import com.exam.digitalwallet.repository.AccountRepository;
import com.exam.digitalwallet.services.AccountManageService;

@RestController
public class AccountController {
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountManageService accountManageService;
	
	/**
	 * Add account with/without initial balance 
	 * @param account
	 * @return saved object
	 */
	@RequestMapping(value="/accounts",method=RequestMethod.POST)
	public ResponseEntity<Account> save(@Valid @RequestBody Account account ) {
		Account savedObj = accountManageService.saveAccount(account);
		return new ResponseEntity<Account>(savedObj,HttpStatus.CREATED);
					
	}
	/**
	 * use balance from account
	 * @param id
	 * @param balanceInfo
	 * @return account object after update the new balance
	 */
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.POST)
	public ResponseEntity<Account> useBalance(@PathVariable String id,@RequestBody Map<String,Integer> balanceInfo) {
		Account updatedAccount= accountManageService.use(id, balanceInfo.get("balance"));
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.CREATED);		
	}
	
	/**
	 * check balance from given account
	 * @param id
	 * @return balance amount of requested account
	 */
	@RequestMapping(value ="/accounts/{id}",method = RequestMethod.GET)
	public ResponseEntity<Integer> checkBalance(@PathVariable String id){
		int balance = accountManageService.checkBalance(id);
		return new ResponseEntity<Integer>(balance,HttpStatus.OK);				 		
	}
	/**
	 * Add balance to given account.extra amount added to
	 * balance according to the promotion codes
	 * @param id
	 * @param recharge
	 * @return account object after updating the balance
	 */
	@RequestMapping(value = "/accounts/{id}/balance",method = RequestMethod.POST)
	public ResponseEntity<Account> addBalance(@PathVariable String id,@RequestBody RechargeRequest recharge) {
		Account account= accountManageService.addBalance(id, recharge);
		return new ResponseEntity<Account>(account,HttpStatus.OK);		
	}
	
	

	
	
}
