package com.example.slstore.admin.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.AffiliateDto;
import com.example.slstore.admin.dto.AffiliateSearchForm;
import com.example.slstore.common.entity.Affiliate;
import com.example.slstore.common.entity.AffiliateDetail;
import com.example.slstore.common.repository.AffiliateDetailRepository;
import com.example.slstore.common.repository.AffiliateRepository;

@Controller
@RequestMapping("/admin/affiliates")
public class AffiliateController {
    @Autowired
    private AffiliateRepository affiliateRepository;

    @Autowired
    private AffiliateDetailRepository affiliateDetailRepository;

    @GetMapping("/index")
    public String list(Model model) {
        model.addAttribute("affiliateSearchForm", new AffiliateSearchForm());
        model.addAttribute("affiliates", affiliateRepository.findAll());
        return "admin/affiliates/list";
    }

    @GetMapping("/addform")
    public String showAddForm(Model model) {
        model.addAttribute("affiliateDto", new AffiliateDto());
        return "admin/affiliates/add-form";
    }

    @PostMapping("/store")
    public String add(AffiliateDto affiliateDto, Model model) {
        Affiliate affiliate = new Affiliate();
        affiliate.setName(affiliateDto.getName());
        affiliate.setIsDeleted(0);
        affiliate.setCreatedAt(LocalDateTime.now());
        affiliate.setUpdatedAt(LocalDateTime.now());
        affiliateRepository.save(affiliate);

        AffiliateDetail detail = new AffiliateDetail();
        detail.setAffiliate(affiliate);
        detail.setEmail(affiliateDto.getEmail());
        detail.setReferralCode(affiliateDto.getReferralCode());
        detail.setCommissionRate(affiliateDto.getCommissionRate());
        detail.setMedia(affiliateDto.getMedia());
        detail.setContractStartDate(affiliateDto.getContractStartDate());
        detail.setContractEndDate(affiliateDto.getContractEndDate());
        detail.setIsDeleted(0);
        detail.setCreatedAt(LocalDateTime.now());
        detail.setUpdatedAt(LocalDateTime.now());

        affiliateDetailRepository.save(detail);

        model.addAttribute("affiliateSearchForm", new AffiliateSearchForm());
        return "admin/affiliates/list";
    }

}