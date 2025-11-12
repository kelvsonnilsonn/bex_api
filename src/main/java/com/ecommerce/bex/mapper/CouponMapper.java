package com.ecommerce.bex.mapper;

import com.ecommerce.bex.command.coupon.CreateCouponCommand;
import com.ecommerce.bex.dto.CouponResponseDTO;
import com.ecommerce.bex.enums.DiscountType;
import com.ecommerce.bex.model.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    default Coupon toCoupon(CreateCouponCommand command){
        return new Coupon(command.code(), DiscountType.fromString(command.type()), command.discountValue(), command.expiresAt(), command.usageLimit());
    }
    CouponResponseDTO toResponse(Coupon coupon);
}
