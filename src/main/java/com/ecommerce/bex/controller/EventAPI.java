package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.EventIntervalDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserEventsIntervalDTO;
import com.ecommerce.bex.event.EventStore;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Events", description = "API para consulta de eventos do sistema (Event Sourcing)")
public interface EventAPI {

    @Operation(summary = "Meus eventos", description = "Busca eventos relacionados ao usuário autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findMyEvents(Pageable pageable);

    @Operation(summary = "Eventos por tipo", description = "Busca eventos por tipo de aggregate (ORDER, PRODUCT, CART) - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findByAggregateType(Pageable pageable, @PathVariable String aggregateType);

    @Operation(summary = "Todos os eventos", description = "Busca todos os eventos do sistema - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findAll(Pageable pageable);

    @Operation(summary = "Eventos por aggregate", description = "Busca eventos de um aggregate específico (ex: pedido #123) - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findByAggregateId(Pageable pageable, @PathVariable Long aggregateId);

    @Operation(summary = "Eventos por usuário", description = "Busca eventos de um usuário específico - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findByUserId(Pageable pageable, @PathVariable Long userId);

    @Operation(summary = "Meus eventos em intervalo", description = "Busca eventos do usuário autenticado dentro de um intervalo de datas")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findMyEventsInInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO);

    @Operation(summary = "Todos eventos em intervalo", description = "Busca todos os eventos do sistema dentro de um intervalo de datas - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable, @RequestBody EventIntervalDTO intervalDTO);

    @Operation(summary = "Eventos de usuário em intervalo", description = "Busca eventos de um usuário específico dentro de um intervalo de datas - Acesso Admin")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable, @RequestBody UserEventsIntervalDTO intervalDTO);
}