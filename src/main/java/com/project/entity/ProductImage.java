package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_PRODUCT_IMAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage extends  AbtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "path")
    private  String path;

    @Column(name = "isRepresent")
    private  Boolean isRepresent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" ,referencedColumnName = "id")
    private Product product;
}
