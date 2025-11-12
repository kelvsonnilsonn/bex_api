package com.ecommerce.bex.handler;

import com.ecommerce.bex.exception.*;
import com.ecommerce.bex.exception.cart.EmptyCartException;
import com.ecommerce.bex.exception.coupon.*;
import com.ecommerce.bex.exception.cart.CartNotFoundException;
import com.ecommerce.bex.exception.order.OrderNotFoundException;
import com.ecommerce.bex.exception.product.InvalidCategoryException;
import com.ecommerce.bex.exception.product.ProductAlreadyReceivedException;
import com.ecommerce.bex.exception.product.ProductNotFoundException;
import com.ecommerce.bex.exception.user.UserNotFoundException;
import com.ecommerce.bex.exception.product.ProductNotInCartException;
import com.ecommerce.bex.exception.user.CPFAlreadyInUseException;
import com.ecommerce.bex.exception.user.EmailAlreadyInUseException;
import com.ecommerce.bex.exception.user.UsernameAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CPFAlreadyInUseException.class)
    public ResponseEntity<String> handleCPFInUse(CPFAlreadyInUseException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<String> handleCouponNotFound(CouponNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CouponLimitException.class)
    public ResponseEntity<String> handleCouponLimit(CouponLimitException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }


    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handleEmailInUse(EmailAlreadyInUseException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    public ResponseEntity<String> handleUsernameInUse(UsernameAlreadyInUseException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationFail(MethodArgumentNotValidException e){
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(CouponAlreadyExistsException.class)
    public ResponseEntity<String> handleCouponExists(CouponAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedCommandException.class)
    public ResponseEntity<String> handleSmallPrivileges(UnauthorizedCommandException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ExpiredCouponException.class)
    public ResponseEntity<String> handleCouponExpired(ExpiredCouponException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCouponTypeException.class)
    public ResponseEntity<String> handleCouponType(InvalidCouponTypeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<String> handleInvalidCategory(InvalidCategoryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<String> handleEmptyCart(EmptyCartException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(FailedLoginAttemptException.class)
    public ResponseEntity<String> handleLoginFail(FailedLoginAttemptException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ProductNotInCartException.class)
    public ResponseEntity<String> handleProductNotInCart(ProductNotInCartException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidDateIntervalException.class)
    public ResponseEntity<String> handleInvalidInterval(InvalidDateIntervalException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sem permissão");
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFound(CartNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ProductAlreadyReceivedException.class)
    public ResponseEntity<String> handleProductAlreadyReceived(ProductAlreadyReceivedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
