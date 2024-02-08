package msa.ShopService.order;

import lombok.Builder;
import lombok.Getter;
import msa.ShopService.util.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Long totalCount;
    private Long totalPrice;
    @Builder.Default
    private LocalDateTime orderedAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private State state;
}
