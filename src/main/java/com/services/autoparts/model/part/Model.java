package com.services.autoparts.model.part;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "main_name")
    private String mainName;
    @Column(name = "sub_name")
    private String subName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "original_model_id", referencedColumnName = "id")
    private List<Part> parts;

    @ManyToMany(mappedBy = "replaceModels")
    private List<Part> replaceParts;
}
