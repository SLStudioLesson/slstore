package com.example.slstore.customer.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/customer/login")
    public String login() {
        return "customer/account/login-form";
    }

    @GetMapping("/mypage")
    public String myPage(Model model) {
        // ここから
        
        
        // ここまで
        return "customer/account/mypage";
    }

    @GetMapping("/mypage/detail")
    public String myPageDetail(Model model) {
        // ここに処理を追記する
        return "customer/account/mypage-detail";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "customer/account/privacy-policy";
    }

    @GetMapping("/terms-of-service")
    public String termsOfService() {
        return "customer/account/terms-of-service";
    }

    @GetMapping("/user-guide")
    public String userGuide() {
        return "customer/account/user-guide";
    }

    @GetMapping("/order-history")
    public String showOrderHistory(Model model) {
        // ここから
        // ここまで
        return "customer/account/order-history";
    }

    @GetMapping("/favorite-products")
    public String showFavoriteProducts(Model model) {
        // ここから
        // ここまで
        return "customer/account/favorite-products";
    }

    @GetMapping("/return-products")
    public String showReturn(Model model) {
        // ここから
        // ここまで
        return "customer/account/return-products";
    }

    @GetMapping("/delivery-status")
    public String showDeliveryStatus(Model model) {
        // ここから
        // ここまで
        return "customer/account/delivery-status";
    }

    @GetMapping("/addresses")
    public String showAddresses(Model model) {
        // ここから
        // ここまで
        return "customer/account/addresses";
    }

    @GetMapping("/cards")
    public String showCards(Model model) {
        // ここから
        // ここまで
        return "customer/account/cards";
    }
}
