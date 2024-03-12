package com.test.portal.account.platform.repository;

import com.test.portal.account.platform.entity.PayPalStandaloneCredit;
import com.test.portal.account.platform.entity.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface DataRepository extends CrudRepository<PayPalStandaloneCredit, Long> {


  // Original Oracle query
  @Query("SELECT credit FROM PayPalStandaloneCredit credit WHERE credit.status = :status "
      + "AND credit.paymentHandle IS NOT NULL AND credit.transactionDate BETWEEN :startDate AND :endDate "
      + "AND ROWNUM <= :count")
  List<PayPalStandaloneCredit> findTopCreditsByStatusAndPaymentHandleNotNull(@Param("status") Status status,
    @Param("startDate") OffsetDateTime start, @Param("endDate") OffsetDateTime end, @Param("count") long count);

  // PostgreSQL
  @Query("SELECT credit FROM PayPalStandaloneCredit credit WHERE credit.status = :status "
          + "AND credit.paymentHandle IS NOT NULL AND credit.transactionDate BETWEEN :startDate AND :endDate "
          + "AND credit.transactionDate = (SELECT MAX(sub.credit.transactionDate) FROM PayPalStandaloneCredit sub "
          + "WHERE sub.status = :status AND sub.paymentHandle IS NOT NULL "
          + "AND sub.transactionDate BETWEEN :startDate AND :endDate "
          + "GROUP BY sub.id ORDER BY MAX(sub.transactionDate) DESC) "
          + "ORDER BY credit.transactionDate DESC")
  List<PayPalStandaloneCredit> findTopPostgress(@Param("status") Status status,
    @Param("startDate") OffsetDateTime start, @Param("endDate") OffsetDateTime end, @Param("count") long count);

}
