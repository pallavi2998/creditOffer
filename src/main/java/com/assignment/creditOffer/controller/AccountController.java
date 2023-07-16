package com.assignment.creditOffer.controller;

import com.assignment.creditOffer.exception.ResourceNotFoundException;
import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import com.assignment.creditOffer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/accounts")
    public List<AccountDetail> getAllAccount(){
        return accountService.fetchAllAccounts();
    }

    //create account
    @PostMapping("/createAccount")
    public long createAccount(@RequestBody AccountDetail accountDetail){
        //TODO: handle if it already exists
        return accountService.addAccount(accountDetail);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDetail> getAccountById(@PathVariable Long id){
        AccountDetail detail= accountService.fetchAccountDetail(id);
        return ResponseEntity.ok(detail);
    }



}
