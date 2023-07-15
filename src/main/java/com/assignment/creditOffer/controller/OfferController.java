package com.assignment.creditOffer.controller;


import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.model.LimitOffer;
import com.assignment.creditOffer.model.OfferStatus;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import com.assignment.creditOffer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OfferController {



    @Autowired
    private OfferService offerService;

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/offers")
    public List<LimitOffer> getAllOffers(){
        return offerService.fetchAllOffers();
    }

    @GetMapping("/activeOffers")
    public List<LimitOffer> getActiveOffers(@RequestParam(value="accountID") Long accountID,
                                            @RequestParam("activeDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date activeDate){
        return offerService.fetchActiveOffers(accountID,activeDate);
    }

    //create offer
    @PostMapping("/createOffer")
    public ResponseEntity<LimitOffer> createOffer(@RequestBody LimitOffer offer){
       return offerService.addOffer(offer);
    }

    // build update employee REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<LimitOffer> updateOffer(@PathVariable Long id,
            @RequestParam(value="offerStatus") OfferStatus status) {
        if(OfferStatus.ACCEPTED.equals(status)){
           return offerService.acceptOffer(id);
        }else{
          return  offerService.rejectOffer(id);
        }
    }
}
