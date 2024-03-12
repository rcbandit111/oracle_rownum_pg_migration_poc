package com.test.portal.account.platform.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "standalone_credits")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class StandaloneCredit {

    @Id
    @Column(name = "internal_id", nullable = false)
    private Long internalId;

    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "reconciliation_id")
    private String reconciliationId;

    @Transient
    private String gatewayResponse;

    private BigDecimal amount;

    @Column(name = "transaction_date")
    private ZonedDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "credit_batch_id")
    private UUID creditBatchId;

    @ManyToOne(optional = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "payment_handle_internal_id",referencedColumnName = "internal_id")
    private PaymentHandle paymentHandle;

    public StandaloneCredit() {
    }
}
