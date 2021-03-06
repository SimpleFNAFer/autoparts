package com.services.autoparts.model.order;

import com.services.autoparts.model.part.Part;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_contents")
public class OrderContents {

    @EmbeddedId
    private OrderContentsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    private Part part;

    private Integer number;
}
