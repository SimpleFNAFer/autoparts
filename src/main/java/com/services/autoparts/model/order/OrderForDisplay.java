package com.services.autoparts.model.order;

import com.services.autoparts.model.part.PartForDisplay;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderForDisplay {
    private Long id;
    private List<PartForDisplay> parts;
    private BigDecimal price;
}
