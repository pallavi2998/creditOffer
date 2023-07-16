package com.assignment.creditOffer.service;

import com.assignment.creditOffer.exception.ResourceNotFoundException;
import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.model.LimitOffer;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public long addAccount(AccountDetail accountDetail){
        accountRepository.save(accountDetail);
        return accountDetail.getAccountID();
    }

    public List<AccountDetail> fetchAllAccounts(){
        return accountRepository.findAll();
    }

    public AccountDetail fetchAccountDetail(Long id){
        return accountRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Account not found with ID"+id));
    }

}
