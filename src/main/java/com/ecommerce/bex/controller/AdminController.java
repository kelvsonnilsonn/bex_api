package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateUsernameByIdCommand;
import com.ecommerce.bex.dto.EventIntervalDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserEventsIntervalDTO;
import com.ecommerce.bex.event.EventStore;
import com.ecommerce.bex.service.EventStoreService;
import com.ecommerce.bex.service.command.UserCommandService;
import com.ecommerce.bex.service.query.CartQueryService;
import com.ecommerce.bex.service.query.OrderQueryService;
import com.ecommerce.bex.service.query.UserQueryService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.ADMIN_PATH)
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
public class AdminController implements AdminAPI {

    private final CartQueryService cartQueryService;
    private final EventStoreService eventStoreService;
    private final OrderQueryService orderQueryService;
    private final UserQueryService userQueryService;

    private final UserCommandService userCommandService;

    // SEÇÃO PARA CARTS

    @GetMapping(AppConstants.CART_BASE_PATH)
    public ResponseEntity<?> findCart(Pageable pageable, @RequestParam(required = false) Long id){
        if(id != null){
            return ResponseEntity.ok(cartQueryService.findCartProducts(pageable, id));
        }
        return ResponseEntity.ok(cartQueryService.findAll(pageable));
    }

    // SEÇÃO PARA EVENTOS

    @GetMapping(AppConstants.EVENT_BASE_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findEvents(Pageable pageable,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long aggregateId,
            @RequestParam(required = false) String aggregateType) {

        if(userId != null){
            return ResponseEntity.ok(eventStoreService.findAllByUserId(pageable, userId));
        }
        if(aggregateId != null){
            return ResponseEntity.ok(eventStoreService.findByAggregateId(pageable, aggregateId));
        }
        if(aggregateType != null){
            return ResponseEntity.ok(eventStoreService.findByAggregateType(pageable, aggregateType));
        }
        return ResponseEntity.ok(eventStoreService.findAllEvents(pageable));
    }

    @PostMapping(AppConstants.EVENT_BASE_PATH + AppConstants.EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findAllByInterval(pageable, intervalDTO));
    }

    @PostMapping(AppConstants.EVENT_BASE_PATH + AppConstants.USER_EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable, @RequestBody UserEventsIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findUserEventsByInterval(pageable, intervalDTO));
    }

    // SEÇÃO DE PEDIDOS

    @GetMapping(AppConstants.ORDER_BASE_PATH)
    public ResponseEntity<?> findOrder(Pageable pageable,
            @RequestParam(required = false) Long id){
        if(id != null){
            return ResponseEntity.ok(orderQueryService.findById(id));
        }
        return ResponseEntity.ok(orderQueryService.findAll(pageable));
    }

    // SEÇÃO DE USUÁRIOS

    @GetMapping(AppConstants.USER_BASE_PATH)
    public ResponseEntity<?> findUser(Pageable pageable,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email){

        if(username != null){
            return ResponseEntity.ok(userQueryService.findByName(username));
        }
        if(email != null){
            return ResponseEntity.ok(userQueryService.findByEmail(email));
        }
        return ResponseEntity.ok(userQueryService.findAll(pageable));
    }

    @PutMapping(AppConstants.CHANGE_USERNAME_PATH)
    public ResponseEntity<Void> updateUsernameById(@RequestBody @Valid UpdateUsernameByIdCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }
}
