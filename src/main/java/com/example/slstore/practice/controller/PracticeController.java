package com.example.slstore.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.practice.model.Dog;

@Controller
@RequestMapping("/practice")
public class PracticeController {

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "practice/helloworld";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        String str = "Hello World";
        int number = 1234;
    
        model.addAttribute("str", str);
        model.addAttribute("number", number);
        return "practice/variable";
    }

    @GetMapping("/person")
    public String displayPerson(Model model) {
        // ここから
        // ここまで
        
        return "practice/display-person";
    }

    @GetMapping("/varioustypes")
    public String variousTypes(Model model) {
        // ここから
        // ここまで
    
        return "practice/various-types";
    }

    @GetMapping("/method-overload")
    public String methodOverload(Model model) {
        // ここから
        // ここまで

        return "practice/method-overload";
    }

    @GetMapping("/constructor-overload")
    public String constructorOverload(Model model) {
        // ここから
        // ここまで

        return "practice/constructor-overload";
    }

    @GetMapping("/access-modifier")
    public String publicAccess(Model model) {
        // ここから
        // ここまで

        return "practice/access-modifier";
    }

    @GetMapping("/static")
    public String testStatic(Model model) {
        // ここから
        // ここまで

        return "practice/static-access";
    }

    @GetMapping("/calc-decimal")
    public String calcDecimal(Model model) {
        // ここから
        // ここまで

        return "practice/calc-decimal";
    }

    @GetMapping("/date-operation")
    public String dateOperation(Model model) {
        // ここから
        // ここまで

        return "practice/date-operation";
    }

    @GetMapping("/animal")
    public String animal(Model model) {
        Dog dog = new Dog("ポチ", 5, "柴犬");
        model.addAttribute("dog", dog);
        return "practice/animal";
    }
}
