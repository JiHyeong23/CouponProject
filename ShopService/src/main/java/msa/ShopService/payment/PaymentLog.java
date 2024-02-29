package msa.ShopService.payment;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "Log", timeToLive = 3000)
@AllArgsConstructor
public class PaymentLog implements Serializable {
    private Long userId;
    @Id
    private Long orderId;

}
