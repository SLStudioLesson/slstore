package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.common.entity.PromotionCode;
import com.example.slstore.common.repository.PromotionCodeRepository;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionCodeController {

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @GetMapping("index")
    public String list(Model model) {
        List<PromotionCode> promotionCodes = promotionCodeRepository.findAll();
        model.addAttribute("promotionCodes", promotionCodes);
        return "admin/promotions/list";
    }

}