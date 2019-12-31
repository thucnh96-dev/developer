package com.project.service;


import com.project.entity.Product;
import com.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;

    @Override
    public List<Product> findByNameIsLike(String name) {
        return productRepository.findByNameIsLike(name);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Page<Product> findAll(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification,pageable);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
