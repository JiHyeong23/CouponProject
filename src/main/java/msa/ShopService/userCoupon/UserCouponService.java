package msa.ShopService.userCoupon;

import lombok.AllArgsConstructor;
import msa.ShopService.coupon.Coupon;
import msa.ShopService.coupon.CouponRepository;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserCouponService {
    private UserCouponRepository userCouponRepository;
    private CouponRepository couponRepository;
    private UtilMethods utilMethods;

    @Transactional
    public ResponseDto issueCoupon(String code) {
        Coupon coupon = couponRepository.findByCode(code);
        int quantity = coupon.getQuantity();
        ResponseDto responseDto;
        if (quantity > 0) {
            coupon.setQuantity(quantity - 1);
            //coupon.decreaseQuantity();
            couponRepository.save(coupon);

            UserCoupon userCoupon = UserCoupon.builder()
                    .userId(1L)
                    .couponCode(coupon.getCode())
                    .build();
            userCouponRepository.save(userCoupon);
            return responseDto = utilMethods.makeSuccessResponseDto("Successfully saved", userCoupon.getCouponCode());
        } else {
            return responseDto = utilMethods.makeFailResponseDto("Coupon exhausted", quantity);
        }
    }
//    public ResponseDto issueCoupon(String code, Long userId) {
//
//    }
}
