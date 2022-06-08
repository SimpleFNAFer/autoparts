package com.services.autoparts.model.order;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class OrderContentsKey implements Serializable {

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "part_id")
    private Long partId;
}
