package com.services.autoparts.model.cart;

import com.services.autoparts.model.part.Part;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cart_contents")
public class CartContents {

    @EmbeddedId
    private CartContentsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    private Part part;
    private Integer number;
}
