package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.coupon.CreateCouponCommand;
import com.ecommerce.bex.command.coupon.DeleteCouponCommand;
import com.ecommerce.bex.command.coupon.UpdateCouponExpireDateCommand;
import com.ecommerce.bex.command.coupon.UpdateCouponLimitCommand;
import com.ecommerce.bex.event.coupon.CouponCreatedEvent;
import com.ecommerce.bex.event.coupon.CouponDeletedEvent;
import com.ecommerce.bex.event.coupon.CouponExpireUpdateEvent;
import com.ecommerce.bex.event.coupon.CouponLimitUpdateEvent;
import com.ecommerce.bex.exception.coupon.CouponAlreadyExistsException;
import com.ecommerce.bex.exception.coupon.CouponNotFoundException;
import com.ecommerce.bex.mapper.CouponMapper;
import com.ecommerce.bex.model.Coupon;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.repository.CouponRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import com.ecommerce.bex.service.EventStoreService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponCommandService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final EventStoreService eventStoreService;
    private final AuthenticationInformation authenticationInformation;

    public Long create(CreateCouponCommand command){
        findByCode(command.code());
        Coupon coupon = couponMapper.toCoupon(command);
        Coupon savedCoupon = couponRepository.save(coupon);

        CouponCreatedEvent event = new CouponCreatedEvent(savedCoupon.getId(),
                authenticationInformation.getAuthenticatedUser().getId(), coupon.getCode(),
                coupon.getDiscountValue(), coupon.getUsageLimit(), coupon.getExpiresAt());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_COUPON_TYPE, coupon.getId(), event);

        return savedCoupon.getId();
    }

    public void delete(DeleteCouponCommand command) {
        Coupon coupon = findCoupon(command.couponId());
        couponRepository.delete(coupon);
        CouponDeletedEvent event = new CouponDeletedEvent(coupon.getId(),
                authenticationInformation.getAuthenticatedUser().getId(), command.reason());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_COUPON_TYPE, coupon.getId(), event);
    }

    public void update(UpdateCouponLimitCommand command){
        Coupon coupon = findCoupon(command.couponId());
        int oldLimit = coupon.getUsageLimit();
        coupon.setUsageLimit(command.newUsageLimit());
        couponRepository.save(coupon);
        CouponLimitUpdateEvent event = new CouponLimitUpdateEvent(coupon.getId(),
                authenticationInformation.getAuthenticatedUser().getId(), oldLimit, coupon.getUsageLimit());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_COUPON_TYPE, coupon.getId(), event);
    }

    public void update(UpdateCouponExpireDateCommand command){
        Coupon coupon = findCoupon(command.couponId());
        LocalDateTime oldExpireDate = coupon.getExpiresAt();
        coupon.setExpiresAt(command.newExpireDate());
        couponRepository.save(coupon);
        CouponExpireUpdateEvent event = new CouponExpireUpdateEvent(coupon.getId(),
                authenticationInformation.getAuthenticatedUser().getId(), oldExpireDate, coupon.getExpiresAt());
        eventStoreService.saveEvent(AppConstants.AGGREGATE_COUPON_TYPE, coupon.getId(), event);
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
