package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.service.OrderService;
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

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> order(){
        OrderResponseDTO response = orderService.order();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(AppConstants.ALL_DATA_SEARCH_PATH)
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> findAll(Pageable pageable){
        PageResponseDTO<OrderResponseDTO> orders = orderService.findAll(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<OrderResponseDTO>> findMyOrders(Pageable pageable){
        PageResponseDTO<OrderResponseDTO> orders = orderService.findMyOrder(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(AppConstants.ID_PATH)
    public ResponseEntity<OrderResponseDTO> findOrder(@PathVariable Long id){
        OrderResponseDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping(AppConstants.ID_PATH + AppConstants.UPDATE_STATUS_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_SELLER_REQUISITION + " or " + AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long id){
        OrderResponseDTO order = orderService.setOrderNextStatus(id);
        return ResponseEntity.ok(order);
    }

}
