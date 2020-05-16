package com.rodion.telenor.service;

import com.rodion.telenor.dao.ProductDao;
import com.rodion.telenor.dao.PropertyDao;
import com.rodion.telenor.domain.*;
import com.rodion.telenor.mapper.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String FILE_SOURCE = "src/main/resources/data.csv";
    private static final String PROPERTY_NAME_COLOR = "color";
    private static final String PROPERTY_NAME_GB_LIMIT = "gb_limit";
    private static final String PROPERTY_SEPARATOR = ":";
    private static final String TABLE_SEPARATOR = ",";
    private static final String QUOTE = "\"";

    private ProductDao productDao;

    //clear this if not needed
    private PropertyDao propertyDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, PropertyDao propertyDao) {
        this.productDao = productDao;
        this.propertyDao = propertyDao;
    }

    @Override
    public DataResponse findAll(ProductSearchParameters parameters) {
        return DataResponse.newBuilder()
                .withData(ProductMapper.mapToList(productDao.findAll(ProductSpecification.createSearchSpecification(parameters))))
                .build();
    }

    @Override
    public InfoResponse loadDataToDatabase() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_SOURCE));
        reader.lines().skip(1).forEach(this::saveToDatabase);
        return InfoResponse.newBuilder()
                .withResponse("Data added to in-memory DB")
                .build();
    }

    private void saveToDatabase(String l) {
        String[] line = l.replaceAll(QUOTE, "").split(TABLE_SEPARATOR);

        String propertyAsString = line[1];
        Property property = null;
        if (propertyAsString.contains(PROPERTY_NAME_COLOR)) {
            property = Property.newBuilder().withColor(StringUtils.substringAfter(propertyAsString, PROPERTY_SEPARATOR)).build();
        } else if (propertyAsString.contains(PROPERTY_NAME_GB_LIMIT)) {
            property = Property.newBuilder().withGbLimit(Integer.valueOf(StringUtils.substringAfter(propertyAsString, PROPERTY_SEPARATOR))).build();
        }

        productDao.save(ProductMapper.map(Product.newBuilder()
                .withCity(line[3] + TABLE_SEPARATOR + line[4])
                .withPrice(Double.parseDouble(line[2]))
                .withProperty(property)
                .withType(line[0])
                .build()));
    }
}
