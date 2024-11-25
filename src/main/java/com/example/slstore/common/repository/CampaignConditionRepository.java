package com.example.slstore.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstore.common.entity.CampaignCondition;

@Repository
public interface CampaignConditionRepository extends JpaRepository<CampaignCondition, Integer> {
    
}
