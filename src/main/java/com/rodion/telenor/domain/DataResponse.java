package com.rodion.telenor.domain;

import java.util.List;

public class DataResponse extends ValueObject {
    private static final long serialVersionUID = 1L;

    private List<Product> data;

    private DataResponse(Builder builder) {
        this.data = builder.data;
    }

    public List<Product> getData() {
        return data;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{data};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Product> data;

        private Builder() {
        }

        public Builder withData(List<Product> data) {
            this.data = data;
            return this;
        }

        public DataResponse build() {
            return new DataResponse(this);
        }
    }
}
