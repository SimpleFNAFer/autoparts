package com.services.autoparts.controller;

import com.services.autoparts.OrderService;
import com.services.autoparts.model.order.OrderForDisplay;
import com.services.autoparts.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/orders")
    public String getOrders(User user, Model model) {
        List<OrderForDisplay> orders = orderService.getOrders(user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/order/create")
    public String createOrder(User user) {
        orderService.createOrder(user);
        return "redirect:/orders";
    }
}
