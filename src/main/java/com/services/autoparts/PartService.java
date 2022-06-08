package com.services.autoparts;

import com.services.autoparts.model.part.Part;
import com.services.autoparts.model.part.PartForDisplay;
import com.services.autoparts.repo.ModelRepository;
import com.services.autoparts.repo.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final ModelRepository modelRepository;
    public Iterable<PartForDisplay> getPartsList(String modelName) {
        String mainName = modelName.split(" ")[0];
        String subName = modelName.split(" ").length > 1 ? modelName.split(" ")[1] : null;

        List<Part> parts = modelRepository.findPartsByMainName(mainName);
        List<PartForDisplay> partsForDisplay = new ArrayList<>();

        for( Part part : parts) {
            PartForDisplay p = new PartForDisplay();
            p.setType(part.getType().getName());
            p.setOriginalModel(part.getOriginalModel().getMainName());
            p.setSupplier(part.getSupplier().getName());
            p.setPrice(part.getPrice());

            partsForDisplay.add(p);
        }

        return partsForDisplay;
    }
}
