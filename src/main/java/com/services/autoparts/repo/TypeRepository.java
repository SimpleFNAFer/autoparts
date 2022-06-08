package com.services.autoparts.repo;

import com.services.autoparts.model.part.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
