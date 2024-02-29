package msa.ShopService.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    //@Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Coupon findByCode(String code);
}
