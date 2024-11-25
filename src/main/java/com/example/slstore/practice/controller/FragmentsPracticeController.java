package com.example.slstore.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/practice")
public class FragmentsPracticeController {
    @GetMapping("/fragment")
    public String testFragment(Model model) {
        // ここから
        // ここまで

        return "practice/A";
    }
}
