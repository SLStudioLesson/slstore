package com.example.slstore.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.UserForm;
import com.example.slstore.common.entity.User;
import com.example.slstore.common.repository.UserRepository;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String list(Model model) {
        List<User> users = userRepository.findAll();
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        model.addAttribute("users", users);
        return "admin/customer/list";
    }

    @GetMapping("/search")
    public String search(UserForm userForm, Model model) {
        List<User> users = userRepository.findAll();
        List<User> searchUsers = new ArrayList<>();
        String inputName = userForm.getName();

        for (User user : users) {
            if (user.getFirstName().equals(inputName) || user.getLastName().equals(inputName)) {
                searchUsers.add(user);
            }
        }

        model.addAttribute("users", searchUsers);
        return "admin/customer/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "admin/customer/detail";
    }
}
