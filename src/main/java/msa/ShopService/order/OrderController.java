package msa.ShopService.order;

import lombok.AllArgsConstructor;
import msa.ShopService.order.dto.GetOrderIdDto;
import msa.ShopService.order.dto.OrderCreationDto;
import msa.ShopService.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity findOrder(@RequestBody GetOrderIdDto getOrderIdDto, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getHeader("Authorization-Id"));
        ResponseDto responseDto = orderService.getOrder(getOrderIdDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
