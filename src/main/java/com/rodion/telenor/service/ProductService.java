package com.rodion.telenor.service;

import com.rodion.telenor.domain.DataResponse;
import com.rodion.telenor.domain.InfoResponse;
import com.rodion.telenor.domain.ProductSearchParameters;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

public interface ProductService {
    DataResponse findAll(ProductSearchParameters parameters);

    InfoResponse loadDataToDatabase() throws FileNotFoundException;
}
