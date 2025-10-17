package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateUsernameByIdCommand;
import com.ecommerce.bex.dto.*;
import com.ecommerce.bex.event.EventStore;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Administração", description = "API para operações administrativas do sistema - Acesso exclusivo para administradores")
public interface AdminAPI {

    // ========== SEÇÃO CARTS ==========

    @Operation(summary = "Buscar carrinhos", description = """
        Busca carrinhos por diferentes critérios.
        Prioridade: id > todos.
        Exemplos:
        - ?id=123 → Itens do carrinho específico
        - Sem parâmetros → Todos os carrinhos (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Carrinho não encontrado")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<?> findCart(Pageable pageable, @RequestParam(required = false) Long id);

    // ========== SEÇÃO EVENTOS ==========

    @Operation(summary = "Buscar eventos", description = """
        Busca eventos por diferentes critérios.
        Prioridade: userId > aggregateId > aggregateType > todos.
        Exemplos:
        - ?userId=456 → Eventos do usuário
        - ?aggregateId=789 → Eventos do agregado
        - ?aggregateType=ORDER → Eventos por tipo
        - Sem parâmetros → Todos eventos (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Parâmetros de filtro inválidos")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findEvents(Pageable pageable,
                                                           @RequestParam(required = false) Long userId,
                                                           @RequestParam(required = false) Long aggregateId,
                                                           @RequestParam(required = false) String aggregateType);

    @Operation(summary = "Buscar eventos em intervalo", description = "Busca todos os eventos em um intervalo de datas específico")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos do intervalo retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Intervalo de datas inválido")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findEventsByInterval(Pageable pageable,
                                                                     @RequestBody @Valid EventIntervalDTO intervalDTO);

    @Operation(summary = "Buscar eventos de usuário em intervalo", description = "Busca eventos de um usuário específico em intervalo de datas")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Eventos do usuário no intervalo retornados com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = "Intervalo de datas inválido")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<EventStore>> findAllUserEventsInInterval(Pageable pageable,
                                                                            @RequestBody @Valid UserEventsIntervalDTO intervalDTO);

    // ========== SEÇÃO PEDIDOS ==========

    @Operation(summary = "Buscar pedidos", description = """
        Busca pedidos por diferentes critérios.
        Prioridade: id > todos.
        Exemplos:
        - ?id=999 → Pedido específico
        - Sem parâmetros → Todos os pedidos (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Pedido não encontrado")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<?> findOrder(Pageable pageable, @RequestParam(required = false) Long id);

    // ========== SEÇÃO USUÁRIOS ==========

    @Operation(summary = "Buscar usuários", description = """
        Busca usuários por diferentes critérios.
        Prioridade: username > email > todos.
        Exemplos:
        - ?username=joao → Usuário por username
        - ?email=joao@email.com → Usuário por email
        - Sem parâmetros → Todos os usuários (paginação)
        """)
    @ApiResponse(responseCode = HttpConstants.OK, description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Usuário não encontrado")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<?> findUser(Pageable pageable,
                               @RequestParam(required = false) String username,
                               @RequestParam(required = false) String email);

    @Operation(summary = "Atualizar username de usuário", description = "Endpoint administrativo para atualizar o username de qualquer usuário do sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Username atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = "Usuário não encontrado")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateUsernameById(@RequestBody @Valid UpdateUsernameByIdCommand command);
}