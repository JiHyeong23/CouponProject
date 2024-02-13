package msa.ShopService.order;

import lombok.AllArgsConstructor;
import msa.ShopService.order.dto.OrderCreationDto;
import msa.ShopService.orderProduct.OrderProduct;
import msa.ShopService.orderProduct.OrderProductRepository;
import msa.ShopService.product.Product;
import msa.ShopService.product.ProductRepository;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderProductRepository orderProductRepository;
    private UtilMethods utilMethods;

    public ResponseDto saveOrder(OrderCreationDto orderCreationDto, Long userId) {
        Order order = Order.builder()
                .userId(userId)
                .orderedAt(LocalDateTime.now())
                .build();
        orderRepository.save(order);

        Long totalCount = 0L;
        Long totalPrice = 0L;

        for (OrderCreationDto.OrderDetails orderDetail  : orderCreationDto.getOrderDetails()) {
            Product product = productRepository.findById(orderDetail.getProductId()).get();
            OrderProduct orderProduct = OrderProduct.builder()
                    .orderId(order)
                    .productId(product)
                    .count(orderDetail.getCounts()).build();
            orderProductRepository.save(orderProduct);
            totalCount += orderDetail.getCounts();
            totalPrice += (product.getPrice() * orderDetail.getCounts());
        }
        order.setTotalCount(totalCount);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully processed", order.getState());
        return responseDto;
    }
}
