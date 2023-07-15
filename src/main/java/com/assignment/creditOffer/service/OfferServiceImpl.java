package com.assignment.creditOffer.service;

import com.assignment.creditOffer.exception.ResourceNotFoundException;
import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.model.LimitOffer;
import com.assignment.creditOffer.model.LimitType;
import com.assignment.creditOffer.model.OfferStatus;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public ResponseEntity<LimitOffer> addOffer(LimitOffer limitOffer){

        long accountID = limitOffer.getAccountID();
        AccountDetail accountDetail= accountRepository.findById(accountID)
                    .orElseThrow(() -> new ResourceNotFoundException("Account does not exist with id:" + accountID));

        //save only if offer expiry is in future and greater than activation time
            if(limitOffer.getOfferExpiryTime().after(limitOffer.getOfferActivationTime())
             && limitOffer.getOfferExpiryTime().after(Date.from(Instant.now()))) {
               //save only if new limit value is greater than existing limit value
                if((LimitType.ACCOUNT.equals(limitOffer.getLimitType()) &&
                        limitOffer.getOfferLimitValue() > accountDetail.getAccountLimit())
                        || (LimitType.PER_TRANSACTION.equals(limitOffer.getLimitType()) &&
                        limitOffer.getOfferLimitValue() > accountDetail.getPerTransactionLimit())) {
                    limitOffer.setOfferStatus(OfferStatus.PENDING);
                    offerRepository.save(limitOffer);
                }
            }

         return ResponseEntity.ok(limitOffer);
    }


    @Override
    public List<LimitOffer> fetchActiveOffers(Long accountID, java.util.Date activeDate){
        List<LimitOffer> activeOffers =
                offerRepository.findByAccountIDAndOfferStatus(accountID,OfferStatus.PENDING)
                .stream().filter(off -> off.getOfferExpiryTime().after(activeDate) &&
                                off.getOfferActivationTime().before(activeDate))
                        .collect(Collectors.toList());
        return activeOffers;
    }


    @Override
    public ResponseEntity<LimitOffer> acceptOffer(Long offerID){
      LimitOffer offer = offerRepository.findById(offerID)
              .orElseThrow(() -> new ResourceNotFoundException("Offer does not exist with id:" + offerID));

        offer.setOfferStatus(OfferStatus.ACCEPTED);
        long accountID = offer.getAccountID();
        AccountDetail accountDetail= accountRepository.findById(accountID)
                .orElseThrow(() -> new ResourceNotFoundException("Account does not exist with id:" + accountID));

        //update account details
        if(LimitType.ACCOUNT.equals(offer.getLimitType())){
            accountDetail.setLastAccountLimit(accountDetail.getAccountLimit());
            accountDetail.setAccountLimit(offer.getOfferLimitValue());
            accountDetail.setAccountLimitUpdatedTime(LocalDateTime.now());
        }else if(LimitType.PER_TRANSACTION.equals(offer.getLimitType())){
            accountDetail.setLastPerTransactionLimit(accountDetail.getPerTransactionLimit());
            accountDetail.setPerTransactionLimit(offer.getOfferLimitValue());
            accountDetail.setPerTransactionLimitUpdatedTime(LocalDateTime.from(Instant.now()));
        }
       offerRepository.save(offer);
       accountRepository.save(accountDetail);
       return ResponseEntity.ok(offer);
    }

    @Override
    public ResponseEntity<LimitOffer> rejectOffer(Long offerID){
        LimitOffer offer = offerRepository.findById(offerID)
                .orElseThrow(() -> new ResourceNotFoundException("Offer does not exist with id:" + offerID));

        offer.setOfferStatus(OfferStatus.REJECTED);
        offerRepository.save(offer);
        return ResponseEntity.ok(offer);
    }
    @Override
    public List<LimitOffer> fetchAllOffers(){
        return offerRepository.findAll();
    }
}
