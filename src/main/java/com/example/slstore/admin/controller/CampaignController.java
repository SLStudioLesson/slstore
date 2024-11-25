package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.CampaignForm;
import com.example.slstore.common.entity.Campaign;
import com.example.slstore.common.entity.CampaignCondition;
import com.example.slstore.common.repository.CampaignConditionRepository;
import com.example.slstore.common.repository.CampaignRepository;

@Controller
@RequestMapping("/admin/campaign")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignConditionRepository campaignConditionRepository;

    @GetMapping("index")
    public String list(Model model) {
        List<Campaign> campaigns = campaignRepository.findAll();
        model.addAttribute("campaigns", campaigns);
        return "admin/campaign/list";
    }

    @GetMapping("/addform")
    public String showCreateForm(Model model) {
        model.addAttribute("campaignForm", new CampaignForm());
        return "admin/campaign/add-form";
    }

    @PostMapping("/store")
    public String add(CampaignForm campaignForm) {
        Campaign campaign = new Campaign(campaignForm.getName(), campaignForm.getDescription());
        campaignRepository.save(campaign);

        CampaignCondition campaignCondition = new CampaignCondition(
            campaign, 
            campaignForm.getMinimunPurchase(), 
            campaignForm.getValidFrom(), 
            campaignForm.getValidTo(), 
            campaignForm.getDiscountPercentage());
        campaignConditionRepository.save(campaignCondition);

        return "admin/campaign/list";
    }
}
