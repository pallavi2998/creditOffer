package com.assignment.creditOffer.service;

import com.assignment.creditOffer.model.AccountDetail;

import java.util.List;

public interface AccountService {

    long addAccount(AccountDetail accountDetail);

     List<AccountDetail> fetchAllAccounts();
     AccountDetail fetchAccountDetail(Long id);

}
