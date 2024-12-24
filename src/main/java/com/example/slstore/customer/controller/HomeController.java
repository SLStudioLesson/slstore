package com.example.slstore.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.common.entity.Notice;
import com.example.slstore.common.repository.NoticeRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/shop")
public class HomeController {
    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public String index(Model model) {
        List<Notice> notices = noticeRepository.findAll();
        model.addAttribute("notices", notices);
        return "customer/shop/notices";
    }
}
