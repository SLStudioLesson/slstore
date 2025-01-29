package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.FaqForm;
import com.example.slstore.common.entity.Faq;
import com.example.slstore.common.repository.FaqRepository;

@Controller
@RequestMapping("/admin/faqs")
public class FaqController {

    @Autowired
    private FaqRepository faqRepository;

    @GetMapping("/index")
    public String list(Model model) {
        List<Faq> faqs = faqRepository.findAll();
        FaqForm faqForm = new FaqForm();
        model.addAttribute("faqForm", faqForm);
        model.addAttribute("faqs", faqs);
        return "admin/faqs/list";
    }

}