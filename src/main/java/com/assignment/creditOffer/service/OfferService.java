package com.assignment.creditOffer.service;

import com.assignment.creditOffer.model.LimitOffer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface OfferService {
    ResponseEntity<LimitOffer> addOffer(LimitOffer limitOffer);
     List<LimitOffer> fetchAllOffers();
    List<LimitOffer> fetchActiveOffers(Long accountID, Date activeDate);

    ResponseEntity<LimitOffer> acceptOffer(Long offerID);

    ResponseEntity<LimitOffer> rejectOffer(Long offerID);
}
