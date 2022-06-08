package com.services.autoparts.repo;

import com.services.autoparts.model.part.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
