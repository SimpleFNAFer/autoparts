package com.services.autoparts.model.part;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartForDisplay {
    private Long id;
    private String type;
    private String originalModel;
    private String supplier;
    private BigDecimal price;
    private Integer number;
}
