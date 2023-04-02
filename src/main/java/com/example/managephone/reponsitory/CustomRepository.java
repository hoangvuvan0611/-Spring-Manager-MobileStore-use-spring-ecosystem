package com.example.managephone.reponsitory;

import com.example.managephone.entity.Mobile;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @PostConstruct
    public void init() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Mobile> filterMobileByTypeAndActive(Integer type, Integer active) {
        CriteriaQuery<Mobile> criteriaQuery = criteriaBuilder.createQuery(Mobile.class);
        Root<Mobile> root = criteriaQuery.from(Mobile.class);
        List<Predicate> predicate = new ArrayList<>();
        if (type != null) {
            predicate.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (type != null) {
            predicate.add(criteriaBuilder.equal(root.get("active"), active));
        }
        criteriaQuery.where(predicate.toArray(new Predicate[0]));
        TypedQuery<Mobile> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Mobile> filterMobileList(Integer typeFrom, Integer typeTo,
                                         Integer active,
                                         Date dateFrom, Date dateTo,
                                         String name,
                                         Integer start, Integer limit) {
        CriteriaQuery<Mobile> criteriaQuery = criteriaBuilder.createQuery(Mobile.class);
        Root<Mobile> root = criteriaQuery.from(Mobile.class);
        List<Predicate> predicate = new ArrayList<>();
        if (typeFrom != null && typeTo != null) {
            predicate.add(criteriaBuilder.between(root.get("type"), dateFrom, dateTo));
        }
        if (active != null) {
            predicate.add(criteriaBuilder.equal(root.get("active"), active));
        }
        if (dateFrom != null && dateTo != null) {
            predicate.add(criteriaBuilder.between(root.get("createdTime"), dateFrom, dateTo));
        }
        if (name != null) {
            predicate.add(criteriaBuilder.like(root.get("name"),"%" + name + "%"));
        }
        criteriaQuery.where(predicate.toArray(new Predicate[0]));
        TypedQuery<Mobile> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public static Specification<Mobile> buildFilterSpecification(Integer typeFrom, Integer typeTo,
                                                                 Integer active,
                                                                 Date dateFrom, Date dateTo,
                                                                 String name){
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (typeFrom != null && typeTo != null) {
                predicates.add(criteriaBuilder.between(root.get("type"), dateFrom, dateTo));
            }
            if (active != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), active));
            }
            if (dateFrom != null && dateTo != null) {
                predicates.add(criteriaBuilder.between(root.get("createdTime"), dateFrom, dateTo));
            }
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"),"%" + name + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
