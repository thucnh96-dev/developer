package com.project.service;

import com.project.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findByNameIsLike(String name);

    Category findByName(String name);

    Category save(Category category);

    Optional<Category> findById(Long id);

    Page<Category> findAll(Specification<Category> specification , Pageable pageable);

    void deleteById(long id);
}
