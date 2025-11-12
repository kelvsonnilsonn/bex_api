package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.coupon.CreateCouponCommand;
import com.ecommerce.bex.command.coupon.DeleteCouponCommand;
import com.ecommerce.bex.command.coupon.UpdateCouponExpireDateCommand;
import com.ecommerce.bex.command.coupon.UpdateCouponLimitCommand;
import com.ecommerce.bex.exception.coupon.CouponAlreadyExistsException;
import com.ecommerce.bex.exception.coupon.CouponNotFoundException;
import com.ecommerce.bex.mapper.CouponMapper;
import com.ecommerce.bex.model.Coupon;
import com.ecommerce.bex.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponCommandService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public Long create(CreateCouponCommand command){
        findByCode(command.code());
        Coupon coupon = couponMapper.toCoupon(command);
        Coupon savedCoupon = couponRepository.save(coupon);
        return savedCoupon.getId();
    }

    public void delete(DeleteCouponCommand command) {
        Coupon coupon = findCoupon(command.couponId());
        couponRepository.delete(coupon);
    }

    public void update(UpdateCouponLimitCommand command){
        Coupon coupon = findCoupon(command.couponId());
        coupon.setUsageLimit(command.newUsageLimit());
        couponRepository.save(coupon);
    }

    public void update(UpdateCouponExpireDateCommand command){
        Coupon coupon = findCoupon(command.couponId());
        coupon.setExpiresAt(command.newExpireDate());
        couponRepository.save(coupon);
    }

    private Coupon findCoupon(Long id){
        return couponRepository.findById(id).orElseThrow(CouponNotFoundException::new);
    }

    private void findByCode(String code){
        Optional<Coupon> coupon = couponRepository.findByCode(code);
        if(coupon.isPresent()){
            throw new CouponAlreadyExistsException();
        }
    }
}
