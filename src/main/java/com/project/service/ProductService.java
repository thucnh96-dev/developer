package com.project.service;

import com.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findByNameIsLike(String name);

    Product findByName(String name);

    Page<Product> findAll(Specification<Product> specification , Pageable pageable);

    Optional<Product> findById(long id);

    void deleteById (long id);

}
