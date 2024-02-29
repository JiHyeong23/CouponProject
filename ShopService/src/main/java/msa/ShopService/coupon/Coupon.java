package msa.ShopService.coupon;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Getter
    private String code;
    private LocalDateTime expirationDate;
    private Long discountRate;
    @Setter
    private int quantity;

    public void decreaseQuantity() {
        this.quantity -= 1;
    }

}
