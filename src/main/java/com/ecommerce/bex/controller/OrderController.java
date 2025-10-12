package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.order.CreateOrderCommand;
import com.ecommerce.bex.command.order.UpdateOrderStatusCommand;
import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.service.command.OrderCommandService;
import com.ecommerce.bex.service.query.OrderQueryService;
import com.ecommerce.bex.util.AppConstants;
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
public class OrderController {

    private final OrderCommandService commandService;
    private final OrderQueryService queryService;

    @PostMapping
    public ResponseEntity<Long> order(@RequestBody CreateOrderCommand command){
        Long orderId = commandService.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping(AppConstants.ALL_DATA_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(queryService.findAll(pageable));
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> findMyOrders(Pageable pageable){
        return ResponseEntity.ok(queryService.findMyOrder(pageable));
    }

    @GetMapping(AppConstants.ID_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<OrderResponseDTO> findOrder(@PathVariable Long id){
        return ResponseEntity.ok(queryService.findById(id));
    }

    @PutMapping(AppConstants.UPDATE_STATUS_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updateOrderStatus(@RequestBody UpdateOrderStatusCommand command){
        commandService.updateOrderStatus(command);
        return ResponseEntity.ok().build();
    }
}
