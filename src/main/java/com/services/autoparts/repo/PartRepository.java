package com.services.autoparts.repo;

import com.services.autoparts.model.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByOriginalModelId (Long originalModelId);
    List<Part> findByOriginalModelIdInOrReplaceModelsIn (Iterable<Long> originalModelId,
                                                          Iterable<Long> replaceModelId);
    List<Part> findByReplaceModels(Long replaceModelId);
}
