package com.rodion.telenor.domain;

public class InfoResponse extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String response;

    private InfoResponse(Builder builder) {
        this.response = builder.response;
    }

    public String getResponse() {
        return response;
    }

    @Override
    protected Object[] getIdFields() {
        return new Object[]{response};
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String response;

        private Builder() {
        }

        public Builder withResponse(String response) {
            this.response = response;
            return this;
        }

        public InfoResponse build() {
            return new InfoResponse(this);
        }
    }
}
