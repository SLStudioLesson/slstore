package com.example.slstore.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.Campaign;




@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    Campaign findFirstByOrderByCreatedAtDesc();
}
