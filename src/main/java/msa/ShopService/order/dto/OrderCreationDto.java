package msa.ShopService.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreationDto {
    private List<OrderDetails> orderDetails;

    @Getter
    public static class OrderDetails {
        private Long productId;
        private Long counts;
    }
}
