package com.example.slstore.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.slstore.common.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(int productId);
} 