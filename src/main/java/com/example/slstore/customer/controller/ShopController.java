package com.example.slstore.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.common.entity.Brand;
import com.example.slstore.common.repository.BrandRepository;
import com.example.slstore.common.repository.UserFavoriteBrandRepository;
import com.example.slstore.customer.dto.BrandForm;
import com.example.slstore.customer.dto.Cart;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private UserFavoriteBrandRepository userFavoriteBrandRepository;

    @GetMapping("/coupons")
    public String listCoupons(Model model) {
        // ここから
        // ここまで

        return "customer/shop/coupon-list";
    }
    
    @GetMapping("/brand/index")
    public String list(Model model) {
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brandForm", new BrandForm());
        model.addAttribute("brands", brands);
        return "customer/shop/brand-list";
    }

    @GetMapping("/brand/search")
    public String search(BrandForm brandForm, Model model) {
        List<Brand> brands = brandRepository.findAll();
        List<Brand> searchBrands = new ArrayList<>();
        String inputName = brandForm.getName();

        for (Brand brand : brands) {
            if (brand.getName().equals(inputName)) {
                searchBrands.add(brand);
            }
        }

        model.addAttribute("brands", searchBrands);
        return "customer/shop/brand-list";
    }

    @GetMapping("/brand/{id}")
    public String showBrandDetail(@PathVariable("id") int id, Model model) {
        Brand brand = brandRepository.findById(id).get();
        model.addAttribute("brand", brand);
        return "customer/shop/brand-detail";
    }

    @GetMapping("/cart/index")
    public String cartList(Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "customer/shop/cart-list";
    }
}
