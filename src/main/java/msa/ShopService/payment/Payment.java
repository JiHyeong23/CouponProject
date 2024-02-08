package msa.ShopService.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import msa.ShopService.order.Order;
import msa.ShopService.util.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;
    private Long price;
    @Builder.Default
    private LocalDateTime paidAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private State state;
}
