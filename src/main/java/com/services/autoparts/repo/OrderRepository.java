package com.services.autoparts.repo;

import com.services.autoparts.model.order.Order;
import com.services.autoparts.model.order.OrderContentsKey;
import com.services.autoparts.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
