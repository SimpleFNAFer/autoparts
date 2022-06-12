package com.services.autoparts.repo;

import com.services.autoparts.model.cart.Cart;
import com.services.autoparts.model.cart.CartContents;
import com.services.autoparts.model.cart.CartContentsKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartContentsRepository extends JpaRepository<CartContents, CartContentsKey> {
    List<CartContents> findByCart(Cart cart);
}
