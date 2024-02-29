package msa.ShopService.coupon;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CouponQuantityRepository {
    private RedisTemplate<String, String> redisTemplate;
    public Long decrement(String code) {
        return redisTemplate.opsForValue().decrement(code);
    }

    public String getQuantity(String code) {
        return redisTemplate.opsForValue().get(code);
    }

    public void setQuantity(String code, String quantity) {
        redisTemplate.opsForValue().set(code, quantity);
    }

}
