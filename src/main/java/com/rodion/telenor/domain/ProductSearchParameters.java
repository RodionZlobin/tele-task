package com.rodion.telenor.domain;

import com.rodion.telenor.service.ProductSearchParametersField;

public class ProductSearchParameters extends ValueObject {
    private static final long serialVersionUID = 1L;

    @ProductSearchParametersField
    private String city;
    @ProductSearchParametersField
    private Double maxPrice;
    @ProductSearchParametersField
    private Double minPrice;
    @ProductSearchParametersField
    private String property;
    @ProductSearchParametersField
    private String propertyColor;
    @ProductSearchParametersField
    private Integer propertyGbLimitMax;
    @ProductSearchParametersField
    private Integer propertyGbLimitMin;
    @ProductSearchParametersField
    private String type;

    private ProductSearchParameters(Builder builder) {
        this.city = builder.city;
        this.maxPrice = builder.maxPrice;
        this.minPrice = builder.minPrice;
        this.property = builder.property;
        this.propertyColor = builder.propertyColor;
        this.propertyGbLimitMax = builder.propertyGbLimitMax;
        this.propertyGbLimitMin = builder.propertyGbLimitMin;
        this.type = builder.type;
    }

    public String getCity() {
        return city;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public String getProperty() {
        return property;
    }

    public String getPropertyColor() {
        return propertyColor;
    }

    public Integer getPropertyGbLimitMax() {
        return propertyGbLimitMax;
    }

    public Integer getPropertyGbLimitMin() {
        return propertyGbLimitMin;
    }

    public String getType() {
        return type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{city, maxPrice, minPrice, property, propertyColor, propertyGbLimitMax, propertyGbLimitMin, type};
    }

    public static class Builder {
        private String city;
        private Double maxPrice;
        private Double minPrice;
        private String property;
        private String propertyColor;
        private Integer propertyGbLimitMax;
        private Integer propertyGbLimitMin;
        private String type;

        private Builder() {
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withMaxPrice(Double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public Builder withMinPrice(Double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder withProperty(String property) {
            this.property = property;
            return this;
        }

        public Builder withPropertyColor(String propertyColor) {
            this.propertyColor = propertyColor;
            return this;
        }

        public Builder withPropertyGbLimitMax(Integer propertyGbLimitMax) {
            this.propertyGbLimitMax = propertyGbLimitMax;
            return this;
        }

        public Builder withPropertyGbLimitMin(Integer propertyGbLimitMin) {
            this.propertyGbLimitMin = propertyGbLimitMin;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ProductSearchParameters build() {
            return new ProductSearchParameters(this);
        }
    }
}
