package com.assignment.creditOffer.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountDetail {

    @Id
    @NonNull
    private long accountID;

    @NonNull
    private long customerID;

    private double accountLimit;

    private double perTransactionLimit;

    private double lastAccountLimit;

    private double lastPerTransactionLimit;

   // @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime accountLimitUpdatedTime;

    @CreationTimestamp
    private LocalDateTime perTransactionLimitUpdatedTime;

}
