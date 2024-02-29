package msa.ShopService.payment;

import msa.ShopService.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
    @Query("SELECT SUM(p.counts) FROM Payment p WHERE p.state = 'PENDING' GROUP BY p.order.productId HAVING p.order.productId = :productId")
    Long getPendingCounts(Long productId);
}
