package com.services.autoparts.model.cart;

import com.services.autoparts.model.part.PartForDisplay;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartForDisplay {
    List<PartForDisplay> parts;
    BigDecimal finalPrice;
}
