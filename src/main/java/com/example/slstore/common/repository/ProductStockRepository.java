package com.example.slstore.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.ProductStock;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
    @Query("SELECT ps FROM ProductStock ps WHERE ps.product.id = :productId")
    Optional<ProductStock> findByProductId(@Param("productId") int productId);
}