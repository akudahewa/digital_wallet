package com.exam.digitalwallet.component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.exam.digitalwallet.model.Account;
import com.exam.digitalwallet.model.PromotionalBalance;
import com.exam.digitalwallet.repository.AccountRepository;

@Component
public class ScheduleTaskRunner {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Scheduled(cron = "* * 12 * * ?")
	public void expirePromotionalBalance() {
		List<Account> accountList = accountRepository.findAll();
		accountList.forEach(new MyConsumer(accountRepository));
					
	}
}

@Component
class MyConsumer implements Consumer<Account>{

	AccountRepository accountRepository;
	
	public MyConsumer(AccountRepository accountRepository2) {
		this.accountRepository = accountRepository2;
	}

	@Override
	public void accept(Account account) {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime before = account.getPromotionalBalance().getStartDate() ;
		int diff  =dateTime.compareTo(before);
		if(diff >0) {
			PromotionalBalance promo = new PromotionalBalance();
			promo.setBalance(0);
			account.setPromotionalBalance(promo);
			}
		accountRepository.save(account);
	}
	
}
