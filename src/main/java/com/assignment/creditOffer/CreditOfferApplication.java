package com.assignment.creditOffer;

import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CreditOfferApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CreditOfferApplication.class, args);
	}

	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private AccountRepository accountRepository;


	@Override
	public void run(String...args) throws Exception {
//		AccountDetail a1 = new AccountDetail();
//		a1.setAccountID(1234);
//		a1.setCustomerID(2);
//		a1.setAccountLimit(100);
//		a1.setLastAccountLimit(0);
//		a1.setLastPerTransactionLimit(20);
//		a1.setPerTransactionLimit(10);
//		a1.setAccountLimitUpdatedTime(LocalDateTime.now());
//		accountRepository.save(a1);

	}
}
