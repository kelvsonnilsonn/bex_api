package com.ecommerce.bex.service.command;

import com.ecommerce.bex.command.order.CreateOrderCommand;
import com.ecommerce.bex.command.order.UpdateOrderStatusCommand;
import com.ecommerce.bex.command.product.DecreaseStockCommand;
import com.ecommerce.bex.exception.EmptyCartException;
import com.ecommerce.bex.exception.UserNotFoundException;
import com.ecommerce.bex.mapper.OrderMapper;
import com.ecommerce.bex.model.Cart;
import com.ecommerce.bex.model.CartItem;
import com.ecommerce.bex.model.Order;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.user.Address;
import com.ecommerce.bex.repository.CartRepository;
import com.ecommerce.bex.repository.OrderRepository;
import com.ecommerce.bex.service.AuthenticationInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductCommandService commandService;
    private final AuthenticationInformation authenticationInformation;

    public Long create(CreateOrderCommand command){
        User user = authenticationInformation.getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(UserNotFoundException::new);

        if(cart.getItems().isEmpty()){
            throw new EmptyCartException();
        }

        Order order = new Order(user.getId(), cart.getItems());
        setOrderAddress(user.getAddress(), order);
        Order savedOrder = orderRepository.save(order);

        cart.clearCart();
        cartRepository.save(cart);

        for(CartItem item : cart.getItems()){
            commandService.decreaseStock(new DecreaseStockCommand(item.getProductId(), item.getQuantity()));
        }
        return savedOrder.getId();
    }

    public void updateOrderStatus(UpdateOrderStatusCommand command){
        Order order = orderRepository.findById(command.id()).orElseThrow();
        order.nextStatus();
        Order updatedOrder = orderRepository.save(order);
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
