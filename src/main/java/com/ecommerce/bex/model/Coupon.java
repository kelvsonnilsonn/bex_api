package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.DiscountType;
import com.ecommerce.bex.exception.coupon.CouponLimitException;
import com.ecommerce.bex.exception.coupon.ExpiredCouponException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private DiscountType type;

    private BigDecimal discountValue;

    private LocalDateTime expiresAt;
    private int usageLimit;

    public Coupon(String code, DiscountType type, BigDecimal discountValue, LocalDateTime expiresAt, int usageLimit){
        this.code = code;
        this.type = type;
        this.discountValue = discountValue;
        this.expiresAt = expiresAt;
        this.usageLimit = usageLimit;
    }

    public BigDecimal applyCoupon(BigDecimal total){
        if(LocalDateTime.now().isAfter(expiresAt)) {
            throw new ExpiredCouponException();
        }
        if(usageLimit == 0){
            throw new CouponLimitException();
        }
        this.usageLimit -= 1;
        return type.applyCoupon(total, discountValue);
    }
}
