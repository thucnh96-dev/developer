package com.project.repository.specification;

import com.project.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.LinkedList;

public class UserSpecification implements Specification<User> {

    private final Long id;
    private final String name;
    private final String phone;
    private final  boolean isDesc;
    private final String searchKey;
    private final int sortCase;
    private final boolean isAscSort;

    public UserSpecification(Long id, String name, String phone, boolean isDesc, String searchKey, int sortCase, boolean isAscSort) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isDesc = isDesc;
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.isAscSort = isAscSort;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        LinkedList<Predicate> predicates = new LinkedList<>();

        predicates.add(cb.isNull(root.get("deleteAt")));

        if (!StringUtils.isEmpty(name)){
         predicates.add(cb.like(root.get("name"),"% "+this.name+" %"));
        }
        if (!StringUtils.isEmpty(phone)){
            predicates.add(cb.like(root.get("phone"),"% "+this.phone+" %"));
        }

        Path orderClause;
        switch (sortCase) {
            case 1:
                orderClause = root.get("name");
                break;
            case 2:
                orderClause = root.get("phone");
                break;
            case 3:
                orderClause = root.get("createAt");
                break;
            default:
                orderClause = root.get("createAt");
                break;
        }
        if (isAscSort){
            cq.orderBy(cb.asc(orderClause));
        }else {
            cq.orderBy(cb.desc(orderClause));
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
