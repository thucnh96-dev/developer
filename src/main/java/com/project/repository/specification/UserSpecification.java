package com.project.repository.specification;

import com.project.entity.User;
import org.springframework.data.jpa.domain.Specification;

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

    public UserSpecification(Long id, String name, String phone, boolean isDesc, String searchKey, int sortCase, boolean isAscSort, boolean isAscSort1) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isDesc = isDesc;
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.isAscSort = isAscSort1;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        LinkedList<Predicate> predicates = new LinkedList<>();

        predicates.add(cb.isNull(root.get("deleteAt")));

        Path orderClause;
        switch (sortCase) {
            case 1:
                orderClause = root.get("name");
                break;
            case 2:
                orderClause = root.get("salePrice");
                break;
            case 3:
                orderClause = root.get("quantity");
                break;
            case 4:
                orderClause = root.get("createdOn");
                break;
            default: // sort by product name
                orderClause = root.get("createdOn");
        }
        if (isAscSort){
            cq.orderBy(cb.asc(orderClause));
        }else {
            cq.orderBy(cb.desc(orderClause));
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
