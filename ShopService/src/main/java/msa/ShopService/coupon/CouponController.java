package msa.ShopService.coupon;

import lombok.AllArgsConstructor;
import msa.ShopService.coupon.dto.SetQuantityDto;
import msa.ShopService.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon_admin")
@AllArgsConstructor
public class CouponController {
    private CouponService couponService;
    @PostMapping
    public ResponseEntity setCouponQuantity(@RequestBody SetQuantityDto setQuantityDto) {
        couponService.setCouponQuantity(setQuantityDto.getCode(), setQuantityDto.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body("Successfully setting");
    }

    @GetMapping("/{code}")
    public ResponseEntity syncQuantity(@PathVariable String code) {
        ResponseDto responseDto = couponService.syncCouponQuantity(code);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
