package com.rodion.telenor.api;

import com.rodion.telenor.domain.DataResponse;
import com.rodion.telenor.domain.InfoResponse;
import com.rodion.telenor.domain.ProductSearchParameters;
import com.rodion.telenor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<InfoResponse> welcome() {
        InfoResponse response = productService.welcomeMessage();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/data",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<InfoResponse> loadData() throws FileNotFoundException {
        InfoResponse response = productService.loadDataToDatabase();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @RequestMapping(value = "/product",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<DataResponse> getProducts(
            @RequestParam Optional<String> type,
            @RequestParam Optional<Number> min_price,
            @RequestParam Optional<Number> max_price,
            @RequestParam Optional<String> city,
            @RequestParam Optional<String> property,
            @RequestParam Optional<String> property_color,
            @RequestParam Optional<Number> property_gb_limit_min,
            @RequestParam Optional<Number> property_gb_limit_max) throws IllegalAccessException {

        DataResponse response = productService.findAll(ProductSearchParameters.newBuilder()
                .withCity(city.orElse(null))
                .withMaxPrice(max_price.isPresent() ? max_price.get().doubleValue() : null)
                .withMinPrice(min_price.isPresent() ? min_price.get().doubleValue() : null)
                .withProperty(property.orElse(null))
                .withPropertyColor(property_color.orElse(null))
                .withPropertyGbLimitMax(property_gb_limit_max.isPresent() ? property_gb_limit_max.get().intValue() : null)
                .withPropertyGbLimitMin(property_gb_limit_min.isPresent() ? property_gb_limit_min.get().intValue() : null)
                .withType(type.orElse(null))
                .build());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
