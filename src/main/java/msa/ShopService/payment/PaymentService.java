package msa.ShopService.payment;

import lombok.AllArgsConstructor;
import msa.ShopService.order.Order;
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

    public ResponseDto enterPayment(Long userId, Order order) {
        Payment payment = Payment.builder()
                .userId(userId)
                .order(order)
                .counts(order.getTotalCount())
                .build();
        paymentRepository.save(payment);

        PaymentLog log = new PaymentLog(userId, order.getId());
        paymentRedisRepository.save(log);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully entered", payment.getState());
        return responseDto;
    }

    public ResponseDto successPayment(Long userId, Payment payment) {
        payment.setState(State.SUCCESS);
        paymentRepository.save(payment);
        Product product = productRepository.findById(payment.getOrder().getProductId()).get();
        product.updateStock(payment.getCounts());
        productRepository.save(product);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully paid");
        return responseDto;
    }

    public ResponseDto failPayment(Payment payment, Long userId) {
        payment.setState(State.FAILED);
        paymentRepository.save(payment);

        ResponseDto responseDto = utilMethods.makeFailResponseDto("Failed payment", payment.getState());
        return responseDto;
    }
}
