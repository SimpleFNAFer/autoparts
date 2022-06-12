package com.services.autoparts.repo;

import com.services.autoparts.model.cart.CartContents;
import com.services.autoparts.model.cart.CartContentsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartContentsRepository extends JpaRepository<CartContents, CartContentsKey> {
}
