package msa.ShopService;

import msa.ShopService.order.Order;
import msa.ShopService.order.OrderRepository;
import msa.ShopService.payment.Payment;
import msa.ShopService.payment.PaymentLog;
import msa.ShopService.payment.PaymentRedisRepository;
import msa.ShopService.payment.PaymentRepository;
import msa.ShopService.util.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    PaymentRedisRepository paymentRedisRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void saveTest() {
        PaymentLog log = new PaymentLog(1L, 1L);

        paymentRedisRepository.save(log);
    }

    @Test
    void selectTest() {
        System.out.println("@@@@@@@@@@@@@@");
        System.out.println(
                paymentRedisRepository.findByUserId(1L)
        );
    }
}
