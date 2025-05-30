package com.example.slstore.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.AffiliateDetail;

@Repository
public interface AffiliateDetailRepository extends JpaRepository<AffiliateDetail, Integer> {
}
