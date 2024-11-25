package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.slstore.admin.dto.ShippingCompanyDto;
import com.example.slstore.admin.dto.ShippingCompanyListDto;
import com.example.slstore.admin.service.ShippingCompanyService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/shipping-companies")
public class ShippingCompanyController {

    @Autowired
    private ShippingCompanyService shippingCompanyService;

    /**
     * 配送業者一覧画面表示
     */
    @GetMapping
    public String list(Model model) {
        List<ShippingCompanyListDto> companies = shippingCompanyService.getAllShippingCompanies();
        model.addAttribute("companies", companies);
        return "admin/shipping-companies/list";
    }

    /**
     * 配送業者新規登録画面表示
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("shippingCompanyDto", new ShippingCompanyDto());
        return "admin/shipping-companies/create";
    }

    /**
     * 配送業者新規登録処理
     */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute ShippingCompanyDto shippingCompanyDto, 
                        BindingResult result, 
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/shipping-companies/create";
        }

        try {
            shippingCompanyService.createShippingCompany(shippingCompanyDto);
            redirectAttributes.addFlashAttribute("successMessage", "配送業者を登録しました。");
            return "redirect:/admin/shipping-companies";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/shipping-companies/create";
        }
    }

    /**
     * 配送業者編集画面表示
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        try {
            ShippingCompanyDto dto = shippingCompanyService.getShippingCompanyById(id);
            model.addAttribute("shippingCompanyDto", dto);
            return "admin/shipping-companies/edit";
        } catch (NotFoundException e) {
            return "redirect:/admin/shipping-companies";
        }
    }

    /**
     * 配送業者更新処理
     */
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id,
                        @Valid @ModelAttribute ShippingCompanyDto shippingCompanyDto,
                        BindingResult result,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/shipping-companies/edit";
        }

        try {
            shippingCompanyService.updateShippingCompany(id, shippingCompanyDto);
            redirectAttributes.addFlashAttribute("successMessage", "配送業者情報を更新しました。");
            return "redirect:/admin/shipping-companies";
        } catch (Exception  e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/shipping-companies/edit";
        }
    }

    /**
     * 配送業者削除処理
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            shippingCompanyService.deleteShippingCompany(id);
            redirectAttributes.addFlashAttribute("successMessage", "配送業者を削除しました。");
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/shipping-companies";
    }
}
