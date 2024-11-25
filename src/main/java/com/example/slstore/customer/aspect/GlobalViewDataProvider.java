package com.example.slstore.customer.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.slstore.common.repository.BrandRepository;
import com.example.slstore.common.repository.CategoryRepository;
import com.example.slstore.customer.dto.ProductSearchForm;

@ControllerAdvice
public class GlobalViewDataProvider {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("headerCategories", categoryRepository.findAll());
        model.addAttribute("productSearchForm", new ProductSearchForm());

        model.addAttribute("headerBrands", brandRepository.findAll());
    }
}
