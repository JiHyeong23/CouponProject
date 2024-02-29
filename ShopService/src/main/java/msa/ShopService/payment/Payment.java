package msa.ShopService.payment;

import lombok.*;
import msa.ShopService.order.Order;
import msa.ShopService.util.State;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", unique = true)
    private Order order;
    private Long counts;
    private Long price;
    @Builder.Default
    private LocalDateTime paidAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Setter
    private State state = State.PENDING;
}
