package com.rodion.telenor.service;

import com.google.common.collect.Sets;
import com.rodion.telenor.dao.ProductDao;
import com.rodion.telenor.domain.*;
import com.rodion.telenor.exception.EmptyParameterException;
import com.rodion.telenor.mapper.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String FILE_NAME = "/data.csv";
    private static final String PROPERTY_NAME_COLOR = "color";
    private static final String PROPERTY_NAME_GB_LIMIT = "gb_limit";
    private static final String PROPERTY_DELIMITER = ":";
    private static final String TABLE_DELIMITER = ",";
    private static final String QUOTES = "\"";

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResponse findAll(ProductSearchParameters parameters) throws IllegalAccessException {
        if (getFields(parameters).isEmpty()) {
            throw new EmptyParameterException("All parameters are empty");
        }

        return DataResponse.newBuilder()
                .withData(ProductMapper.mapToProductsList(ProductMapper.mapToProductList(productDao.findAll(ProductSpecification.createSearchSpecification(parameters)))))
                .build();
    }

    @Override
    public InfoResponse loadDataToDatabase() throws FileNotFoundException {
        try (InputStream inputStream = getClass().getResourceAsStream(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            reader.lines().skip(1).forEach(this::saveToDatabase);
            return InfoResponse.newBuilder()
                    .withResponse("Data added to in-memory DB")
                    .build();
        } catch (Exception e) {
            throw new FileNotFoundException("Failed to handle file with name " + FILE_NAME);
        }
    }

    @Override
    public InfoResponse welcomeMessage() {
        return InfoResponse.newBuilder()
                .withResponse("Welcome to dockerized springboot task")
                .build();
    }

    private Set<Object> getFields(ProductSearchParameters parameters) throws IllegalAccessException {
        Set<Field> declaredFields = Arrays.stream(parameters.getClass().getDeclaredFields())
                .filter(f -> Objects.nonNull(f.getAnnotation(ProductSearchParametersField.class)))
                .collect(Collectors.toSet());

        Set<Object> fields = Sets.newHashSet();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (Objects.nonNull(field.get(parameters))) {
                fields.add(String.valueOf(field.get(parameters)));
            }
        }
        return fields;
    }

    private void saveToDatabase(String l) {
        try {
            String[] line = l.replaceAll(QUOTES, "").split(TABLE_DELIMITER);

            String propertyAsString = line[1];
            Property property = null;
            if (propertyAsString.contains(PROPERTY_NAME_COLOR)) {
                property = Property.newBuilder().withColor(StringUtils.substringAfter(propertyAsString, PROPERTY_DELIMITER)).build();
            } else if (propertyAsString.contains(PROPERTY_NAME_GB_LIMIT)) {
                property = Property.newBuilder().withGbLimit(Integer.valueOf(StringUtils.substringAfter(propertyAsString, PROPERTY_DELIMITER))).build();
            }

            productDao.save(ProductMapper.map(Product.newBuilder()
                    .withCity(line[3] + TABLE_DELIMITER + line[4])
                    .withPrice(Double.parseDouble(line[2]))
                    .withProperty(property)
                    .withType(line[0])
                    .build()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to save data to DB");
        }
    }
}
