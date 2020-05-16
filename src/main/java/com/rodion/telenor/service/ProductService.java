package com.rodion.telenor.service;

import com.rodion.telenor.domain.DataResponse;
import com.rodion.telenor.domain.InfoResponse;
import com.rodion.telenor.domain.ProductSearchParameters;

import java.io.FileNotFoundException;

public interface ProductService {
    DataResponse findAll(ProductSearchParameters parameters);

    InfoResponse loadDataToDatabase() throws FileNotFoundException;
}
