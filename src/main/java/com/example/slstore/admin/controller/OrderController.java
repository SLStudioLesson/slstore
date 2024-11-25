package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.OrderForm;
import com.example.slstore.common.entity.Order;
import com.example.slstore.common.repository.OrderRepository;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/index")
    public String list(Model model) {
        List<Order> orders = orderRepository.findAll();
        OrderForm orderForm = new OrderForm();
        model.addAttribute("orders", orders);
        model.addAttribute("orderForm", orderForm);
        return "admin/order/list";
    }

    @GetMapping("/search")
    public String search(OrderForm orderForm, Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin/order/list";
    }
}
