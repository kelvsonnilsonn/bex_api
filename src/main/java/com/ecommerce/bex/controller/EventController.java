package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.EventIntervalDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserEventsIntervalDTO;
import com.ecommerce.bex.event.EventStore;
import com.ecommerce.bex.service.EventStoreService;
import com.ecommerce.bex.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.EVENT_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class EventController implements EventAPI {

    private final EventStoreService eventStoreService;

    /**
     * PROCURA EVENTOS DO USUÁRIO LOGADO
     */
    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEvents(Pageable pageable) {
        return ResponseEntity.ok(eventStoreService.findMyEvents(pageable));
    }

    /**
     * PROCURA EVENTOS DE UM DETERMINADO TIPO DE PRODUTOS
     */
    @GetMapping(AppConstants.AGGREGATE_EVENT_BY_TYPE_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findByAggregateType(Pageable pageable, @PathVariable String aggregateType){
        return ResponseEntity.ok(eventStoreService.findByAggregateType(pageable, aggregateType));
    }

    /**
     * PROCURA TODOS OS EVENTOS DO SISTEMA
     */
    @GetMapping(AppConstants.ALL_DATA_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findAll(Pageable pageable) {
        return ResponseEntity.ok(eventStoreService.findAllEvents(pageable));
    }

    /**
     * PROCURA EVENTOS DE UM DETERMINADO PRODUTO
     */
    @GetMapping(AppConstants.AGGREGATE_EVENT_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findByAggregateId(Pageable pageable, @PathVariable Long aggregateId) {
        return ResponseEntity.ok(eventStoreService.findByAggregateId(pageable, aggregateId));
    }

    /**
     * PROCURA EVENTOS DE UM DETERMINADO USUÁRIO
     */
    @GetMapping(AppConstants.USER_EVENT_SEARCH_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findByUserId(Pageable pageable, @PathVariable Long userId) {
        return ResponseEntity.ok(eventStoreService.findAllByUserId(pageable, userId));
    }

    /**
     * MEUS EVENTOS EM INTERVALO
     */
    @PostMapping(AppConstants.MY_EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEventsInInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findMyEventsByInterval(pageable, intervalDTO));
    }

    /**
     * TODOS EVENTOS EM INTERVALO
     */
    @PostMapping(AppConstants.EVENTS_IN_INTERVAL_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findAllByInterval(pageable, intervalDTO));
    }

    /**
     * EVENTOS DE USUÁRIO ESPECÍFICO EM INTERVALO
     */
    @PostMapping(AppConstants.USER_EVENTS_IN_INTERVAL_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable, @RequestBody UserEventsIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findUserEventsByInterval(pageable, intervalDTO));
    }
}