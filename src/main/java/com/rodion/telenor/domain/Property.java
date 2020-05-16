package com.rodion.telenor.domain;

public class Property extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String color;
    private Integer gb_limit;

    private Property(Builder builder) {
        this.color = builder.color;
        this.gb_limit = builder.gb_limit;
    }

    public String getColor() {
        return color;
    }

    public Integer getGb_limit() {
        return gb_limit;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{color, gb_limit};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String color;
        private Integer gb_limit;

        private Builder() {
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withGbLimit(Integer gb_limit) {
            this.gb_limit = gb_limit;
            return this;
        }

        public Property build() {
            return new Property(this);
        }
    }
}
