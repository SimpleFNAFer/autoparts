package com.services.autoparts;

import com.services.autoparts.model.part.Model;
import com.services.autoparts.model.part.Part;
import com.services.autoparts.model.part.PartForDisplay;
import com.services.autoparts.repo.ModelRepository;
import com.services.autoparts.repo.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final ModelRepository modelRepository;
    public Iterable<PartForDisplay> getPartsList(String modelName) {
        String mainName = modelName.split(" ")[0];

        List<Model> models = modelRepository.findByMainName(mainName);
        List<Part> parts = new ArrayList<>();

        for( Model model : models ) {
            parts.addAll(model.getParts());
        }

        return partsForDisplay(parts);
    }

    public PartForDisplay getPartById(Long id) {
        Optional<Part> part = partRepository.findById(id);
        if(part.isEmpty()) throw new RuntimeException("Part with id "+id+" not found");
        return partForDisplay(part.get());
    }

    public Iterable<PartForDisplay> getReplacesById(Long id) {
        Optional<Part> part = partRepository.findById(id);
        List<Part> replaces = new ArrayList<>();

        if (part.isEmpty()) throw new RuntimeException("Part with id "+id+" not found");
        for (Model model : part.get().getReplaceModels()) {
            for (Part repl : model.getParts()) {
                if (repl.getType().equals(part.get().getType())) replaces.add(repl);
            }
        }

        return partsForDisplay(replaces);
    }

    public PartForDisplay partForDisplay(Part part) {
        PartForDisplay partForDisplay = new PartForDisplay();
        partForDisplay.setId(part.getId());
        partForDisplay.setPrice(part.getPrice());
        partForDisplay.setOriginalModel(part.getOriginalModel().getMainName());
        partForDisplay.setSupplier(part.getSupplier().getName());
        partForDisplay.setType(part.getType().getName());

        return partForDisplay;
    }

    public Iterable<PartForDisplay> partsForDisplay(Iterable<Part> parts) {
        List<PartForDisplay> partsForDisplay = new ArrayList<>();

        for (Part part: parts) {
            partsForDisplay.add(partForDisplay(part));
        }

        return partsForDisplay;
    }
}
