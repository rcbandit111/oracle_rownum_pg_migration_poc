package com.test.portal.account.platform.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment_handles")
@Inheritance(strategy = InheritanceType.JOINED)
public class PaymentHandle {

    @Id
    @Column(name = "internal_id", nullable = false)
    private Long internalId;

}
