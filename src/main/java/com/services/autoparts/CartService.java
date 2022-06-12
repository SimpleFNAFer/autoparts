package com.services.autoparts;

import com.services.autoparts.model.cart.Cart;
import com.services.autoparts.model.cart.CartContents;
import com.services.autoparts.model.cart.CartContentsKey;
import com.services.autoparts.model.cart.CartForDisplay;
import com.services.autoparts.model.part.Part;
import com.services.autoparts.model.part.PartForDisplay;
import com.services.autoparts.model.user.User;
import com.services.autoparts.repo.CartContentsRepository;
import com.services.autoparts.repo.CartRepository;
import com.services.autoparts.repo.PartRepository;
import com.services.autoparts.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartContentsRepository cartContentsRepository;
    private PartRepository partRepository;
    private UserRepository userRepository;


    public Long lastId() {
        List<Cart> carts = cartRepository.findAll();
        if(carts.isEmpty()) return 0L;
        return carts.get(carts.size() - 1).getId();
    }
    public void addToCart(Long part_id, User user) {
        user = userRepository.findById(1L).get();
        Part part = new Part();
        boolean alreadyIn = false;
        if(partRepository.findById(part_id).isPresent()) part = partRepository.findById(part_id).get();
        Cart userCart = new Cart();
        List<CartContents> contents = new ArrayList<>();

        if(cartRepository.findByUser(user).isPresent()) {
            userCart = cartRepository.findByUser(user).get();
            contents = userCart.getContents();
            for(CartContents c : contents) {
                if(c.getPart().equals(part)) {
                    c.setNumber(c.getNumber() + 1);
                    alreadyIn = true;
                    break;
                }
            }
            if (!alreadyIn) {
                CartContents c = new CartContents();
                CartContentsKey cck = new CartContentsKey();
                cck.setCartId(userCart.getId());
                cck.setPartId(part.getId());
                c.setNumber(1);
                c.setId(cck);
                c.setCart(userCart);
                c.setPart(part);
                contents.add(c);
            }

            userCart.setPrice(userCart.getPrice().add(part.getPrice()));
            userCart.setContents(contents);
        } else {
            userCart.setUser(user);
            userCart.setId(lastId() + 1);
            userCart.setPrice(part.getPrice());
            CartContents c = new CartContents();
            c.setNumber(1);
            CartContentsKey cck = new CartContentsKey();
            cck.setCartId(userCart.getId());
            cck.setPartId(part_id);
            c.setId(cck);
            c.setCart(userCart);
            c.setPart(part);
            contents.add(c);

            userCart.setContents(contents);
        }
        cartRepository.save(userCart);
    }

    public CartForDisplay getCart(User user) {
        user = userRepository.findById(1L).get();
        Cart cart = new Cart();
        CartForDisplay cartForDisplay = new CartForDisplay();
        if(cartRepository.findByUser(user).isPresent()) {
            cart = cartRepository.findByUser(user).get();
        } else {
            cart.setPrice(BigDecimal.ZERO);
            cart.setContents(new ArrayList<>());
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<CartContents> contents = cart.getContents();
        List<PartForDisplay> displayParts = new ArrayList<>();
        for (CartContents c : contents) {
            PartForDisplay p = new PartForDisplay();
            p.setType(c.getPart().getType().getName());
            p.setSupplier(c.getPart().getSupplier().getName());
            p.setOriginalModel(c.getPart().getOriginalModel().getMainName());
            p.setId(c.getPart().getId());
            p.setPrice(c.getPart().getPrice());
            p.setNumber(c.getNumber());

            displayParts.add(p);
        }

        cartForDisplay.setParts(displayParts);
        cartForDisplay.setFinalPrice(cart.getPrice());

        return cartForDisplay;
    }

    public void removeFromCart (User user, Long partId) {
        user = userRepository.findById(1L).get();
        Part partToRemove = new Part();
        if (partRepository.findById(partId).isPresent()) partToRemove = partRepository.findById(partId).get();

        Cart userCart = new Cart();
        if(cartRepository.findByUser(user).isPresent()) userCart = cartRepository.findByUser(user).get();

        List<CartContents> contents = cartContentsRepository.findByCart(userCart);
        List<CartContents> newContents = new ArrayList<>();
        BigDecimal newPrice = BigDecimal.ZERO;
        for (CartContents c : contents) {
            if (!Objects.equals(c.getPart(), partToRemove)) {
                newPrice=newPrice.add(c.getPart().getPrice().multiply(BigDecimal.valueOf(c.getNumber())));
                newContents.add(c);
            }
            cartContentsRepository.delete(c);
        }
        userCart.setPrice(newPrice);
        userCart.setContents(newContents);
        cartRepository.save(userCart);
    }
}
