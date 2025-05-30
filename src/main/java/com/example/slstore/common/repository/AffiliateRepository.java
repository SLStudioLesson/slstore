package com.example.slstore.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.Affiliate;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Integer> {
}