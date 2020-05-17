package com.rodion.telenor.service;

import com.rodion.telenor.domain.ProductSearchParameters;
import com.rodion.telenor.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {
    public static final String CITY = "city";
    public static final String COLOR = "color";
    public static final String GB_LIMIT = "gb_limit";
    public static final String PRE_POST_FIX = "%";
    public static final String PRICE = "price";
    public static final String PROPERTY = "property";
    public static final String TYPE = "type";

    public static Specification<ProductEntity> createSearchSpecification(ProductSearchParameters parameters) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(parameters.getType())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(TYPE)), parameters.getType().toLowerCase())));
            }
            if (Objects.nonNull(parameters.getCity())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(CITY)), PRE_POST_FIX + parameters.getCity().toLowerCase() + PRE_POST_FIX)));
            }
            if (Objects.nonNull(parameters.getMinPrice())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), parameters.getMinPrice())));
            }
            if (Objects.nonNull(parameters.getMaxPrice())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), parameters.getMaxPrice())));
            }
            if (Objects.nonNull(parameters.getProperty())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.isNotNull(root.join(PROPERTY).get(parameters.getProperty()))));
            }
            if (Objects.nonNull(parameters.getPropertyColor())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.join(PROPERTY).get(COLOR), parameters.getPropertyColor().toLowerCase())));
            }
            if (Objects.nonNull(parameters.getPropertyGbLimitMin())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.join(PROPERTY).get(GB_LIMIT), Double.valueOf(parameters.getPropertyGbLimitMin()))));
            }
            if (Objects.nonNull(parameters.getPropertyGbLimitMax())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.join(PROPERTY).get(GB_LIMIT), Double.valueOf(parameters.getPropertyGbLimitMin()))));
            }

            int predicateSize = predicates.size();
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicateSize]));
        };
    }
}
