package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.CouponResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.exception.coupon.CouponNotFoundException;
import com.ecommerce.bex.mapper.CouponMapper;
import com.ecommerce.bex.model.Coupon;
import com.ecommerce.bex.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponQueryService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public Coupon findByCode(String code){
        return couponRepository.findByCode(code).orElseThrow(CouponNotFoundException::new);
    }

    public PageResponseDTO<CouponResponseDTO> findAvailableCoupons(Pageable pageable){
        Page<CouponResponseDTO> coupons = couponRepository.findByExpiresAtAfter(pageable, LocalDateTime.now()).map(couponMapper::toResponse);
        return PageResponseDTO.fromPage(coupons);
    }

    public PageResponseDTO<CouponResponseDTO> findAll(Pageable pageable){
        Page<CouponResponseDTO> coupons = couponRepository.findAll(pageable).map(couponMapper::toResponse);
        return PageResponseDTO.fromPage(coupons);
    }
}
