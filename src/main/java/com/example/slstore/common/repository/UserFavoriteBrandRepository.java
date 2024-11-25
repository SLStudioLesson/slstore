package com.example.slstore.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.slstore.common.entity.UserFavoriteBrand;

public interface UserFavoriteBrandRepository extends JpaRepository<UserFavoriteBrand, Integer> {
    UserFavoriteBrand findByUserIdAndBrandId(int userId, int brandId);

    int countByBrandId(int brandId);
}