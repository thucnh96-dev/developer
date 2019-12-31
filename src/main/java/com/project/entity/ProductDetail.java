package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_PRODUCT_DETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail extends AbtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "information")
    private String information;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
