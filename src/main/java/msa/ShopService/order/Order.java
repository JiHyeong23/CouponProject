package msa.ShopService.order;

import lombok.*;
import msa.ShopService.payment.Payment;
import msa.ShopService.util.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Setter
    private Long productId;
    private Long totalCount;
    private Long totalPrice;
    @Builder.Default
    private LocalDateTime orderedAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Setter
    private State state = State.PENDING;
    @OneToOne(mappedBy = "order")
    private Payment payment;

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
