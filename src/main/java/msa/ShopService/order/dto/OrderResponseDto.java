package msa.ShopService.order.dto;

import lombok.Builder;
import lombok.Getter;
import msa.ShopService.util.State;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderResponseDto {
    private Long userId;
    private Long productId;
    private Long totalCount;
    private Long totalPrice;
    private State state;
    private LocalDateTime orderedAt;
}
