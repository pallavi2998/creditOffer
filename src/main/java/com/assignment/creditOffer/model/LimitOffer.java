package com.assignment.creditOffer.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limitOffer")
public class LimitOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offerID;

    @NonNull
    private long accountID;

    @NonNull
    private double offerLimitValue;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerActivationTime;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerExpiryTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private LimitType limitType;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("PENDING")
    private OfferStatus offerStatus;


}
