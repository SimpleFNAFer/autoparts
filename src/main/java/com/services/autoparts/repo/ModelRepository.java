package com.services.autoparts.repo;

import com.services.autoparts.model.part.Model;
import com.services.autoparts.model.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Long> findPartsByMainNameAndSubName(String mainName, String subName);
    List<Part> findPartsByMainName(String mainName);
}
