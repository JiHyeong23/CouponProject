package msa.ShopService.order;

import lombok.AllArgsConstructor;
import msa.ShopService.order.dto.OrderCreationDto;
import msa.ShopService.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping
    public ResponseEntity makeOrder(@RequestBody OrderCreationDto orderCreationDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        ResponseDto responseDto = orderService.saveOrder(orderCreationDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
