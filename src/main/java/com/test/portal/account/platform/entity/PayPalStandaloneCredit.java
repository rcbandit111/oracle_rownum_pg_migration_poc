package com.test.portal.account.platform.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "paypal_standalone_credits")
public class PayPalStandaloneCredit extends StandaloneCredit {

  @Column(name = "id")
  private UUID gatewayId;

  @Column(name = "paypal_item_id")
  private String paypalItemId;

  @Column(name = "paypal_transaction_id")
  private String paypalTransactionId;

  @Enumerated(EnumType.STRING)
  @Column(name = "paypal_status")
  private TransactionStatus paypalStatus;

  @Column(name = "consumer_message")
  private String consumerMessage;

  @Column(name = "consumer_id")
  private String consumerId;

}
