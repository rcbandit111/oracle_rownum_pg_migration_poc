package com.test.portal.account.platform.service;

import com.test.portal.account.platform.entity.PayPalStandaloneCredit;
import com.test.portal.account.platform.entity.Status;
import com.test.portal.account.platform.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class DataProcess {

  private long batchSize = 100;

  private DataRepository creditRepository;

  @Autowired
  public DataProcess(DataRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  private void queryOracle() {

    OffsetDateTime startDate = OffsetDateTime.now().minusMinutes(batchSize);
    OffsetDateTime endDate = OffsetDateTime.now();

    List<PayPalStandaloneCredit> creditList = creditRepository
        .findTopCreditsByStatusAndPaymentHandleNotNull(Status.RECEIVED, startDate, endDate, batchSize);

  }

  private void queryPostgress() {

    OffsetDateTime startDate = OffsetDateTime.now().minusMinutes(batchSize);
    OffsetDateTime endDate = OffsetDateTime.now();

    List<PayPalStandaloneCredit> creditList = creditRepository
            .findTopCreditsByStatusAndPaymentHandleNotNull(Status.RECEIVED, startDate, endDate, batchSize);
  }
  /*
  This is one Approach...
  Apply limit programmatically
  */
   private List<PayPalStandaloneCredit> queryPostgress() {

    OffsetDateTime startDate = OffsetDateTime.now().minusMinutes(batchSize);
    OffsetDateTime endDate = OffsetDateTime.now();

    List<PayPalStandaloneCredit> creditList = creditRepository
            .findCreditsByStatusAndDateAndLimit(Status.RECEIVED, startDate, endDate);

      // Apply limit programmatically
    if (creditList.size() > batchSize) {
      creditList = creditList.subList(0, batchSize);
    }   
    return  creditList; 
  }
}
