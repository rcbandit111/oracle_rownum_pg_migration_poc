import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PostGDataRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PayPalStandaloneCredit> findCreditByStatusAndDateAndLimit(String status, Date startDate, Date endDate, int count) {
        TypedQuery<PayPalStandaloneCredit> query = entityManager.createQuery(
            "SELECT credit FROM PayPalStandaloneCredit credit " +
            "WHERE credit.status = :status " +
            "AND credit.paymentHandle IS NOT NULL " +
            "AND credit.transactionDate BETWEEN :startDate AND :endDate", PayPalStandaloneCredit.class);
        query.setParameter("status", status);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setMaxResults(count);

        return query.getResultList();
    }
}
