package msa.ShopService.payment;

import lombok.AllArgsConstructor;
import msa.ShopService.order.Order;
import msa.ShopService.order.OrderRepository;
import msa.ShopService.product.Product;
import msa.ShopService.product.ProductRepository;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.State;
import msa.ShopService.util.UtilMethods;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private UtilMethods utilMethods;
    private final PaymentRedisRepository paymentRedisRepository;
    private ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ResponseDto paymentTest(Long userID, Order order) {
        Payment payment = enterPayment(userID, order);
        if (Math.random() < 0.2) {
            return failPayment(userID, payment);
        } else {
            return successPayment(userID, payment);
        }
    }

    public Payment enterPayment(Long userId, Order order) {
        Payment payment = Payment.builder()
                .userId(userId)
                .order(order)
                .counts(order.getTotalCount())
                .build();
        paymentRepository.save(payment);

        PaymentLog log = new PaymentLog(userId, order.getId());
        paymentRedisRepository.save(log);

        //ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully entered", payment.getState());
        return payment;
    }

    public ResponseDto successPayment(Long userId, Payment payment) {
        payment.setState(State.SUCCESS);
        paymentRepository.save(payment);
        Product product = productRepository.findById(payment.getOrder().getProductId()).get();
        product.updateStock(payment.getCounts());
        productRepository.save(product);

        Order order = payment.getOrder();
        order.setState(State.SUCCESS);
        orderRepository.save(order);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully paid");
        return responseDto;
    }

    public ResponseDto failPayment(Long userId, Payment payment) {
        payment.setState(State.FAILED);
        paymentRepository.save(payment);

        ResponseDto responseDto = utilMethods.makeFailResponseDto("Failed payment", payment.getState());
        return responseDto;
    }
}
