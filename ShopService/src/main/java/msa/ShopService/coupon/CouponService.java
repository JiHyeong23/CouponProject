package msa.ShopService.coupon;

import lombok.AllArgsConstructor;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CouponService {
    private CouponQuantityRepository couponQuantityRepository;
    private CouponRepository couponRepository;
    private UtilMethods utilMethods;

    public void setCouponQuantity(String code, int quantity) {
        Coupon coupon = couponRepository.findByCode(code);
        coupon.setQuantity(quantity);
        couponRepository.save(coupon);

        couponQuantityRepository.setQuantity(code, String.valueOf(quantity));
    }

    public ResponseDto syncCouponQuantity(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        int mysqlQuantity = coupon.getQuantity();
        int redisQuantity = Integer.parseInt(couponQuantityRepository.getQuantity(code));

        ResponseDto responseDto;
        if (mysqlQuantity != redisQuantity) {
            coupon.setQuantity(redisQuantity);
            couponRepository.save(coupon);

            return responseDto = utilMethods.makeSuccessResponseDto("Successfully synchronized");
        }
        return responseDto = utilMethods.makeSuccessResponseDto("already synchronized");
    }
}
