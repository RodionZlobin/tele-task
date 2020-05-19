package com.rodion.telenor.domain;

public class ApiError extends ValueObject {
    private String message;

    public ApiError(Builder builder) {
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{message};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String message;

        private Builder() {
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
