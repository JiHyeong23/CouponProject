package msa.ShopService.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRedisRepository extends CrudRepository<PaymentLog, Long> {
    PaymentLog findByUserId(Long userId);
}
