package msa.ShopService.userCoupon;

import lombok.AllArgsConstructor;
import msa.ShopService.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
@AllArgsConstructor
public class UserCouponController {
    private UserCouponService userCouponService;

    @GetMapping("/{code}")
    public ResponseEntity getCoupon(@PathVariable String code) {
        ResponseDto responseDto = userCouponService.issueCoupon(code);
        System.out.println(responseDto.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/code")
    public String test() {
        System.out.println("Success");
        return "the first";
    }
}
