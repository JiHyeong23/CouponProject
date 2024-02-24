package msa.ShopService.payment;

import lombok.AllArgsConstructor;
import msa.ShopService.order.Order;
import msa.ShopService.payment.dto.EnterPaymentDto;
import msa.ShopService.payment.dto.GetPaymentIdDto;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;
    private UtilMethods utilMethods;

    @PostMapping("/enter")
    public ResponseEntity enterPayment(@RequestBody EnterPaymentDto enterPaymentDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        Order order = utilMethods.findOrderById(enterPaymentDto.getOrderId());

        //ResponseDto responseDto = paymentService.enterPayment(userId, order);
        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully entered");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/success")
    public ResponseEntity successPayment(@RequestBody GetPaymentIdDto getPaymentIdDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        Payment payment = utilMethods.findPaymentById(getPaymentIdDto.getPaymentId());

        ResponseDto responseDto = paymentService.successPayment(userId, payment);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/fail")
    public ResponseEntity failPayment(@RequestBody GetPaymentIdDto getPaymentIdDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        Payment payment = utilMethods.findPaymentById(getPaymentIdDto.getPaymentId());

        ResponseDto responseDto = paymentService.failPayment(userId, payment);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity payment(@RequestBody EnterPaymentDto enterPaymentDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        Order order = utilMethods.findOrderById(enterPaymentDto.getOrderId());

        ResponseDto responseDto = paymentService.paymentTest(userId, order);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
