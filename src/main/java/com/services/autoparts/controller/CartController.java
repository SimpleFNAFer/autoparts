package com.services.autoparts.controller;

import com.services.autoparts.CartService;
import com.services.autoparts.model.cart.CartForDisplay;
import com.services.autoparts.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CartController {

    private CartService cartService;

    @GetMapping("/cart")
    public String getCart(User user, Model model) {
        CartForDisplay cart = cartService.getCart(user);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add-to-cart/{partId}")
    public String addToCart(@PathVariable Long partId, User user) {
        cartService.addToCart(partId,user);
        return "redirect:/cart";
    }

    @PostMapping("remove/{partId}")
    public String removeFromCart(@PathVariable Long partId, User user) {
        cartService.removeFromCart(user, partId);
        return "redirect:/cart";
    }
}
