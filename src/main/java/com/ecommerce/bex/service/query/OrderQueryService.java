package com.ecommerce.bex.service.query;

import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.exception.CartNotFoundException;
import com.ecommerce.bex.mapper.OrderMapper;
import com.ecommerce.bex.model.Order;
import com.ecommerce.bex.repository.OrderRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AuthenticationInformation authenticationInformation;

    @Cacheable("orders")
    public OrderResponseDTO findById(Long orderId){
        System.out.println("🎯 BUSCANDO PEDIDO NO BANCO: " + orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(CartNotFoundException::new);
        return orderMapper.toResponse(order);
    }

    public PageResponseDTO<OrderResponseDTO> findAll(Pageable pageable){
        Page<OrderResponseDTO> orders = orderRepository.findAll(pageable).map(orderMapper::toResponse);
        return PageResponseDTO.fromPage(orders);
    }

    @Cacheable("user-orders")
    public PageResponseDTO<OrderResponseDTO> findMyOrder(Pageable pageable){
        System.out.println("🎯 BUSCANDO MEUS PEDIDOS NO BANCO");
        Long userId = authenticationInformation.getAuthenticatedUser().getId();
        Page<OrderResponseDTO> orders = orderRepository.findByUserId(pageable, userId).map(orderMapper::toResponse);
        return PageResponseDTO.fromPage(orders);
    }
}
