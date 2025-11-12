package com.ecommerce.bex.controller;

import com.ecommerce.bex.service.command.CouponCommandService;
import com.ecommerce.bex.service.query.CouponQueryService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.COUPON_PATH)
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class CouponController {

    private final CouponCommandService couponCommandService;
    private final CouponQueryService couponQueryService;

    @GetMapping
    public ResponseEntity<?> findCoupon(Pageable pageable,
                                        @RequestParam(required = false) String code){
        if(code != null){
            return ResponseEntity.ok(couponQueryService.findByCode(code));
        }
        return ResponseEntity.ok(couponQueryService.findAvailableCoupons(pageable));
    }
}
