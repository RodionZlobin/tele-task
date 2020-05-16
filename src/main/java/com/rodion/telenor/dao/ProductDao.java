package com.rodion.telenor.dao;

import com.rodion.telenor.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
}
