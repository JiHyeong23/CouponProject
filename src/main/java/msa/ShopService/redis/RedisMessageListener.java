package msa.ShopService.redis;

import msa.ShopService.payment.Payment;
import msa.ShopService.payment.PaymentRepository;
import msa.ShopService.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisMessageListener extends KeyExpirationEventMessageListener {
    @Autowired
    private PaymentRepository paymentRepository;
    //private List<CacheManager> cacheManagers;

    public RedisMessageListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        System.out.println(message);
        String messageLong = message.toString();

        Long orderId = Long.valueOf(messageLong.substring(4));
        Payment payment = paymentRepository.findByOrderId(orderId);
        payment.setState(State.TIMEOUT);
        paymentRepository.save(payment);
    }
}