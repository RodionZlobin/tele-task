package com.rodion.telenor.mapper;

import com.rodion.telenor.domain.Product;
import com.rodion.telenor.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
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

    public static List<Product> mapToList(List<ProductEntity> entities) {
        return entities.stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList());
    }
}
