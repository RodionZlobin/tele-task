package com.rodion.telenor.mapper;

import com.rodion.telenor.domain.Property;
import com.rodion.telenor.entity.PropertyEntity;

public class PropertyMapper {
    private PropertyMapper() {
    }

    public static Property map(PropertyEntity entiy) {
        return entiy == null ? null :
                Property.newBuilder()
                        .withColor(entiy.getColor())
                        .withGbLimit(entiy.getGb_limit())
                        .build();
    }

    public static PropertyEntity map(Property property) {
        return property == null ? null :
                PropertyEntity.newBuilder()
                        .withColor(property.getColor())
                        .withGbLimit(property.getGb_limit())
                        .build();
    }
}
