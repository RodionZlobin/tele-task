package com.rodion.telenor.service;

import com.rodion.telenor.domain.ProductSearchParameters;
import com.rodion.telenor.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static final String CITY = "city";
    public static final String COLOR = "color";
    public static final String GB_LIMIT = "gb_limit";
    public static final String PRE_POST_FIX = "%";
    public static final String PRICE = "price";
    public static final String PROPERTY = "property";
    public static final String TYPE = "type";

    public static Specification<ProductEntity> createSearchSpecification(ProductSearchParameters parameters) {
        return new Specification<ProductEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductEntity> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (parameters.getType() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(TYPE)), parameters.getType().toLowerCase())));
                }
                if (parameters.getCity() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(CITY)), PRE_POST_FIX + parameters.getCity().toLowerCase() + PRE_POST_FIX)));
                }
                if (parameters.getMinPrice() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), parameters.getMinPrice())));
                }
                if (parameters.getMaxPrice() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), parameters.getMaxPrice())));
                }
                if (parameters.getProperty() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.isNotNull(root.join(PROPERTY).get(parameters.getProperty()))));
                }
                if (parameters.getPropertyColor() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.join(PROPERTY).get(COLOR), parameters.getPropertyColor().toLowerCase())));
                }
                if (parameters.getPropertyGbLimitMin() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.join(PROPERTY).get(GB_LIMIT), Double.valueOf(parameters.getPropertyGbLimitMin()))));
                }
                if (parameters.getPropertyGbLimitMax() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.join(PROPERTY).get(GB_LIMIT), Double.valueOf(parameters.getPropertyGbLimitMin()))));
                }

                int predicateSize = predicates.size();
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicateSize]));
            }
        };
    }
}
