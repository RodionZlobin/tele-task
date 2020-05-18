package com.rodion.telenor.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rodion.telenor.domain.ApiProduct;
import com.rodion.telenor.domain.Product;
import com.rodion.telenor.domain.Property;
import com.rodion.telenor.entity.ProductEntity;
import com.rodion.telenor.service.ProductsField;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ProductMapper {
    private static final String PROPERTY_DELIMITER = ",";
    private static final String VALUE_DELIMITER = ":";

    private ProductMapper() {
    }

    public static Product map(ProductEntity entity) {
        return entity == null ? null :
                Product.newBuilder()
                        .withCity(entity.getCity())
                        .withPrice(entity.getPrice())
                        .withProperty(PropertyMapper.map(entity.getProperty()))
                        .withType(entity.getType())
                        .build();
    }

    public static ProductEntity map(Product product) {
        return product == null ? null :
                ProductEntity.newBuilder()
                        .withType(product.getType())
                        .withPrice(product.getPrice())
                        .withCity(product.getCity())
                        .withProperty(PropertyMapper.map(product.getProperty()))
                        .build();
    }

    public static List<Product> mapToProductList(List<ProductEntity> entities) {
        return entities.stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList());
    }

    public static ApiProduct mapToProducts(Product product) throws IllegalAccessException {
        return product == null ? null :
                ApiProduct.newBuilder()
                        .withCity(product.getCity())
                        .withPrice(product.getPrice())
                        .withProperties(ProductMapper.mapNotNullPropertiesToString(product.getProperty()))
                        .withType(product.getType())
                        .build();
    }

    public static List<ApiProduct> mapToProductsList(List<Product> entities) throws IllegalAccessException {
        List<ApiProduct> products = Lists.newArrayList();
        if (entities.isEmpty()) {
            return products;
        }
        for (Product product : entities) {
            products.add(ProductMapper.mapToProducts(product));
        }
        return products;
    }

    private static String mapNotNullPropertiesToString(Property property) throws IllegalAccessException {
        Set<Field> declaredFields = Arrays.stream(property.getClass().getDeclaredFields())
                .filter(f -> Objects.nonNull(f.getAnnotation(ProductsField.class)))
                .collect(Collectors.toSet());
        Map<String, String> fields = Maps.newHashMap();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (Objects.nonNull(field.get(property))) {
                fields.put(field.getName(), String.valueOf(field.get(property)));
            }
        }
        return fields.entrySet().stream()
                .map(f -> f.getKey() + VALUE_DELIMITER + f.getValue())
                .collect(Collectors.joining(PROPERTY_DELIMITER));
    }
}
