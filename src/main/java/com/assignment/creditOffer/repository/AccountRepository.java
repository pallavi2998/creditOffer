package com.assignment.creditOffer.repository;

import com.assignment.creditOffer.model.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDetail,Long> {
}
