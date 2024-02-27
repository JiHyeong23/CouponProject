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
        System.out.println("@@@@@@@@@@@@@@@@@@ " + coupon.getQuantity() + " @@@@@@@@@@@@@@@@@@@@");
        UserCoupon userCoupon = UserCoupon.builder()
                .userId(1L)
                .couponCode(coupon.getCode())
                .build();
        userCouponRepository.save(userCoupon);

        coupon.decreaseQuantity();
        couponRepository.save(coupon);

        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully saved", userCoupon.getCouponCode());
        return responseDto;
    }
//    public ResponseDto issueCoupon(String code, Long userId) {
//
//    }
}
