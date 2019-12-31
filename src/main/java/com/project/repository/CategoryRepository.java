package com.project.repository;

import com.project.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByNameIsLike(String name);

    Category findByName(String name);

    Page<Category> findAll(Specification<Category> specification , Pageable pageable);

    @Query(value = "UPDATE TB_CATEGORY SET DELETE_AT = NOW() WHERE id = ?1",nativeQuery = true)
    void deleteById(long id);

}
