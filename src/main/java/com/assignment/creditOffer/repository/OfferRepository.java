package com.assignment.creditOffer.repository;

import com.assignment.creditOffer.model.LimitOffer;
import com.assignment.creditOffer.model.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<LimitOffer,Long> {
    //all crud methods
    List<LimitOffer> findByAccountIDAndOfferStatus(Long accountID, OfferStatus offerStatus);
}
