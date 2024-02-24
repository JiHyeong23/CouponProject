package msa.ShopService.util;

import lombok.AllArgsConstructor;
import msa.ShopService.order.Order;
import msa.ShopService.order.OrderRepository;
import msa.ShopService.payment.Payment;
import msa.ShopService.payment.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UtilMethods {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public ResponseDto makeSuccessResponseDto(String message) {
        return ResponseDto.builder()
                .result(State.SUCCESS).message(message)
                .build();
    }

    public <T>ResponseDto makeSuccessResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(State.SUCCESS).message(message).response(response)
                .build();
    }

    public <T>ResponseDto makeFailResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(State.FAILED).message(message).response(response)
                .build();
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }
    public Payment findPaymentById(Long paymentId) {return paymentRepository.findById(paymentId).get(); }
}
