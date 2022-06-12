package com.services.autoparts;

import com.services.autoparts.model.cart.Cart;
import com.services.autoparts.model.cart.CartContents;
import com.services.autoparts.model.order.Order;
import com.services.autoparts.model.order.OrderContents;
import com.services.autoparts.model.order.OrderContentsKey;
import com.services.autoparts.model.order.OrderForDisplay;
import com.services.autoparts.model.part.PartForDisplay;
import com.services.autoparts.model.user.User;
import com.services.autoparts.repo.CartContentsRepository;
import com.services.autoparts.repo.CartRepository;
import com.services.autoparts.repo.OrderRepository;
import com.services.autoparts.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private CartContentsRepository cartContentsRepository;

    public Long lastOrderId() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) return 0L;
        return orders.get(orders.size()-1).getId();
    }

    public void createOrder(User user) {
        user = userRepository.findById(1L).get();
        Optional<Cart> userCartOp = cartRepository.findByUser(user);
        Cart userCart = new Cart();
        if(userCartOp.isPresent()) userCart = userCartOp.get();
        if(userCart.getContents().isEmpty()) return;

        Long orderId = lastOrderId() + 1;
        Order userOrder = new Order();
        List<OrderContents> orderContents = new ArrayList<>();
        List<CartContents> cartContents = cartContentsRepository.findByCart(userCart);

        userOrder.setId(orderId);
        userOrder.setUser(user);
        userOrder.setPrice(userCart.getPrice());

        for (CartContents c : cartContents) {
            OrderContents oc = new OrderContents();
            OrderContentsKey ock = new OrderContentsKey();

            ock.setOrderId(orderId);
            ock.setPartId(c.getPart().getId());
            oc.setNumber(c.getNumber());
            oc.setId(ock);
            oc.setPart(c.getPart());
            oc.setOrder(userOrder);
            oc.setNumber(c.getNumber());

            orderContents.add(oc);

            cartContentsRepository.delete(c);
        }

        userOrder.setContents(orderContents);

        userCart.setPrice(BigDecimal.ZERO);
        userCart.setContents(new ArrayList<>());
        userCart.setId(userCart.getId());
        userCart.setUser(user);

        orderRepository.save(userOrder);
    }

    public List<OrderForDisplay> getOrders(User user) {
        user = userRepository.findById(1L).get();
        List<OrderForDisplay> ordersForDisplay = new ArrayList<>();
        List<Order> orders = orderRepository.findByUser(user);

        for (Order order: orders) {
            List<OrderContents> orderContents = order.getContents();
            OrderForDisplay orderForDisplay = new OrderForDisplay();

            List<PartForDisplay> partsForDisplay = new ArrayList<>();
            for(OrderContents oc: orderContents) {
                PartForDisplay p = new PartForDisplay();
                p.setId(oc.getPart().getId());
                p.setPrice(oc.getPart().getPrice());
                p.setNumber(oc.getNumber());
                p.setOriginalModel(oc.getPart().getOriginalModel().getMainName());
                p.setType(oc.getPart().getType().getName());
                p.setSupplier(oc.getPart().getSupplier().getName());

                partsForDisplay.add(p);
            }

            orderForDisplay.setId(order.getId());
            orderForDisplay.setParts(partsForDisplay);
            orderForDisplay.setPrice(order.getPrice());

            ordersForDisplay.add(orderForDisplay);
        }

        return ordersForDisplay;
    }
}
