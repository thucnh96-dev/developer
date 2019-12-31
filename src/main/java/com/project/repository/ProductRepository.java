package com.project.repository;

import com.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNameIsLike(String name);

    Product findByName(String name);

    Page<Product> findAll(Specification<Product> specification , Pageable pageable);

    @Query(value = "UPDATE TB_PRODUCT SET DELETE_AT = NOW() WHERE id = ?1",nativeQuery = true)
    void deleteById(long id);

}
