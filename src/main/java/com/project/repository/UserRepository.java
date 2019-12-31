package com.project.repository;

import com.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByNameIsLike(String name);

    User findByPhone(String phone);

    Page<User> findAll(Specification<User> specification, Pageable pageable);

    User findByUserName(String userName);
}
