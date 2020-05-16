package com.rodion.telenor.dao;

import com.rodion.telenor.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDao extends JpaRepository<PropertyEntity, Long>, JpaSpecificationExecutor<PropertyEntity> {
}
