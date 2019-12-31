package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "publishDay")
    private Date publishDay;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private  Category category;

    @OneToOne(mappedBy = "product")
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    public void  addImages(List<ProductImage> productImage){
        productImages.addAll(productImage);
    }

    public void addImages(ProductImage productImage){
        productImages.add(productImage);
    }

    public void removeImages(ProductImage productImage){
        productImages.remove(productImage);
    }
}
