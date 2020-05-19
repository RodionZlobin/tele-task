package com.rodion.telenor.dao;

import com.rodion.telenor.entity.ProductEntity;
import com.rodion.telenor.entity.PropertyEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testWriteAndReadData() {
        ProductEntity productEntity = ProductEntity.newBuilder()
                .withCity("Stockholm")
                .withPrice(200d)
                .withProperty(PropertyEntity.newBuilder()
                        .withColor("red")
                        .build())
                .withType("phone")
                .build();

        ProductEntity savedProduct = productDao.save(productEntity);

        Optional<ProductEntity> fetchedProduct = productDao.findById(savedProduct.getId());

        Assert.assertTrue(fetchedProduct.isPresent());
        Assert.assertEquals(productEntity.getCity(), fetchedProduct.get().getCity());
        Assert.assertEquals(productEntity.getPrice(), fetchedProduct.get().getPrice());
        Assert.assertEquals(productEntity.getProperty(), fetchedProduct.get().getProperty());
        Assert.assertEquals(productEntity.getType(), fetchedProduct.get().getType());
    }
}