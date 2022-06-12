package com.services.autoparts;

import com.services.autoparts.model.cart.Cart;
import com.services.autoparts.model.cart.CartContents;
import com.services.autoparts.model.order.Order;
import com.services.autoparts.model.order.OrderContents;
import com.services.autoparts.model.order.OrderContentsKey;
import com.services.autoparts.model.order.OrderForDisplay;
import com.services.autoparts.model.part.PartForDisplay;
import com.services.autoparts.model.user.User;
import com.services.autoparts.repo.CartRepository;
import com.services.autoparts.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public Long lastOrderId() {
        List<Order> orders = orderRepository.findAll();
        return orders.get(orders.size()-1).getId();
    }

    public void createOrder(User user) {
        Optional<Cart> userCartOp = cartRepository.findByUser(user);
        Cart userCart = new Cart();
        if(userCartOp.isPresent()) userCart = userCartOp.get();

        Long orderId = lastOrderId() + 1;
        Order userOrder = new Order();
        List<OrderContents> orderContents = new ArrayList<>();
        List<CartContents> cartContents = userCart.getContents();

        for (CartContents c : cartContents) {
            OrderContents oc = new OrderContents();
            OrderContentsKey ock = new OrderContentsKey();

            ock.setOrderId(orderId);
            ock.setPartId(c.getPart().getId());
            oc.setNumber(c.getNumber());

            orderContents.add(oc);
        }

        userOrder.setId(orderId);
        userOrder.setUser(user);
        userOrder.setContents(orderContents);
        userOrder.setPrice(userCart.getPrice());

        orderRepository.save(userOrder);
    }

    public List<OrderForDisplay> getOrders(User user) {
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
