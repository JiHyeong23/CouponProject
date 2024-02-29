package msa.ShopService.order;

import lombok.AllArgsConstructor;
import msa.ShopService.order.dto.GetOrderIdDto;
import msa.ShopService.order.dto.OrderCreationDto;
import msa.ShopService.order.dto.OrderResponseDto;
import msa.ShopService.orderProduct.OrderProduct;
import msa.ShopService.orderProduct.OrderProductRepository;
import msa.ShopService.product.Product;
import msa.ShopService.product.ProductRepository;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
            System.out.println(product.getPrice() + product.getId());
            OrderProduct orderProduct = OrderProduct.builder()
                    .orderId(order)
                    .productId(product)
                    .count(orderDetail.getCounts()).build();
            orderProductRepository.save(orderProduct);
            totalCount += orderDetail.getCounts();
            totalPrice += (product.getPrice() * orderDetail.getCounts());
            order.setProductId(product.getId());
        }
        order.setTotalCount(totalCount);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully processed", order.getState());
        return responseDto;
    }

    public ResponseDto getOrder(GetOrderIdDto getOrderIdDto, Long userId) {
        Order order = orderRepository.findById(getOrderIdDto.getOrderId()).get();
        OrderResponseDto orderResponse = OrderResponseDto.builder()
                .userId(order.getUserId())
                .productId(order.getProductId())
                .totalCount(order.getTotalCount())
                .totalPrice(order.getTotalPrice())
                .state(order.getState())
                .orderedAt(order.getOrderedAt())
                .build();
        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully loaded", orderResponse);
        return responseDto;
    }
}
