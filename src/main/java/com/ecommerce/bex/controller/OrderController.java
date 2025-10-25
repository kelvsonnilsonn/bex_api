package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.order.CancelOrderCommand;
import com.ecommerce.bex.command.order.UpdateOrderStatusCommand;
import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.service.command.OrderCommandService;
import com.ecommerce.bex.service.query.OrderQueryService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.ORDER_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class OrderController implements OrderAPI {

    private final OrderCommandService commandService;
    private final OrderQueryService queryService;

    @PostMapping
    public ResponseEntity<Long> order(){
        Long orderId = commandService.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @DeleteMapping
    public ResponseEntity<Void> cancel(@RequestBody @Valid CancelOrderCommand command){
        commandService.cancel(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> findMyOrders(Pageable pageable){
        return ResponseEntity.ok(queryService.findMyOrder(pageable));
    }

    @PutMapping(AppConstants.UPDATE_STATUS_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusCommand command){
        commandService.updateOrderStatus(command);
        return ResponseEntity.ok().build();
    }
}
