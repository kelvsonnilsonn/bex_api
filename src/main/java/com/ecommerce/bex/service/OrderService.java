package com.ecommerce.bex.service;

import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.exception.CartNotFoundException;
import com.ecommerce.bex.exception.UserNotFoundException;
import com.ecommerce.bex.mapper.OrderMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.Order;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.user.Address;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final AuthenticationInformation authenticationInformation;

    @Transactional
    public OrderResponseDTO order(){
        User user = authenticationInformation.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(UserNotFoundException::new);
        Order order = new Order(user.getId(), cart.getItems());
        setOrderAddress(user.getAddress(), order);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }

    public OrderResponseDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(CartNotFoundException::new);
        return orderMapper.toResponse(order);
    }

    public PageResponseDTO<OrderResponseDTO> findAll(Pageable pageable){
        Page<OrderResponseDTO> orders = orderRepository.findAll(pageable).map(orderMapper::toResponse);
        return PageResponseDTO.fromPage(orders);
    }

    public PageResponseDTO<OrderResponseDTO> findMyOrder(Pageable pageable){
        Long userId = authenticationInformation.getAuthenticatedUser().getId();
        Page<OrderResponseDTO> orders = orderRepository.findByUserId(pageable, userId).map(orderMapper::toResponse);
        return PageResponseDTO.fromPage(orders);
    }

    @Transactional
    public OrderResponseDTO setOrderNextStatus(Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        order.nextStatus();
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toResponse(updatedOrder);
    }

    private void setOrderAddress(Address address, Order order){
        order.setCountry(address.getCountry());
        order.setCity(address.getCity());
        order.setNeighborhood(address.getNeighborhood());;
        order.setZipcode(address.getZipcode());
        order.setStreet(address.getStreet());
        order.setNumber(address.getNumber());
    }
}
