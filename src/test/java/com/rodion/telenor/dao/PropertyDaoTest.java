package com.rodion.telenor.dao;

import com.rodion.telenor.entity.PropertyEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PropertyDaoTest {

    @Autowired
    private PropertyDao propertyDao;

    @Test
    public void writeAndReadData() {
        PropertyEntity propertyEntity = PropertyEntity.newBuilder()
                .withColor("red")
                .build();

        PropertyEntity savedProperty = propertyDao.save(propertyEntity);

        Optional<PropertyEntity> fetchedProperty = propertyDao.findById(savedProperty.getId());

        Assert.assertTrue(fetchedProperty.isPresent());
        Assert.assertEquals(propertyEntity.getColor(), fetchedProperty.get().getColor());
        Assert.assertEquals(propertyEntity.getGb_limit(), fetchedProperty.get().getGb_limit());
    }
}