package msa.ShopService.orderProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msa.ShopService.order.Order;
import msa.ShopService.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Order> orderId = new ArrayList<>();
    @OneToMany
    private List<Product> productId = new ArrayList<>();
    private Long price;
    private Long count;
}
