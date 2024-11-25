package com.example.slstore.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.ShippingCompany;

@Repository
public interface ShippingCompanyRepository extends JpaRepository<ShippingCompany, Long> {
    
    // 配送業者名での検索
    Optional<ShippingCompany> findByName(String name);
    
    // アクティブな配送業者のみ取得
    List<ShippingCompany> findByIsActiveTrue();
    
    // 配送業者名の重複チェック（ID除外）
    @Query("SELECT COUNT(s) > 0 FROM ShippingCompany s WHERE s.name = :name AND s.id != :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);
    
    // 配送日数範囲での検索
    List<ShippingCompany> findByDeliveryDaysMinLessThanEqualAndDeliveryDaysMaxGreaterThanEqual(
        Integer maxDays, Integer minDays);
    
    // カスタムソート（配送日数の短い順）
    @Query("SELECT s FROM ShippingCompany s ORDER BY s.deliveryDaysMin ASC, s.deliveryDaysMax ASC")
    List<ShippingCompany> findAllOrderByDeliveryDays();
}
