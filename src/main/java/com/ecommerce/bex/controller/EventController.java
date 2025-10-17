package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.EventIntervalDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
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

    @GetMapping(value = {"", "/"})
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEvents(Pageable pageable) {
        return ResponseEntity.ok(eventStoreService.findMyEvents(pageable));
    }

    @PostMapping(AppConstants.MY_EVENTS_IN_INTERVAL_PATH)
    public ResponseEntity<PageResponseDTO<EventStore>> findMyEventsInInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO) {
        return ResponseEntity.ok(eventStoreService.findMyEventsByInterval(pageable, intervalDTO));
    }
}