package com.project.service;

import com.project.entity.Category;
import com.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findByNameIsLike(String name) {
        return categoryRepository.findByNameIsLike(name);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<Category> findAll(Specification<Category> specification, Pageable pageable) {
        return categoryRepository.findAll(specification,pageable);
    }

    @Override
    public void deleteById(long id) {
        categoryRepository.deleteById(id);
    }
}
