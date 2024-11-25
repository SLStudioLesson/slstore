package com.example.slstore.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // public List<Brand> findByNameContaining(String name);
}
