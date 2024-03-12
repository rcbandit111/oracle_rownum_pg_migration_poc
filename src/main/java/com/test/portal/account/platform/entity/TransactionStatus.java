package com.test.portal.account.platform.entity;

public enum TransactionStatus {
  SUCCESS(Status.COMPLETED);

  private Status status;
  
  TransactionStatus(Status status) {
    this.status = status;
  }
  
  public Status getTransactionStatus() {
    return status;
  }
}
