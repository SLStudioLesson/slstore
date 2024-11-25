package com.example.slstore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstore.admin.dto.AdjustStockForm;
import com.example.slstore.common.entity.ProductStock;
import com.example.slstore.common.entity.StockChangeLog;
import com.example.slstore.common.repository.ProductStockRepository;
import com.example.slstore.common.repository.StockChangeLogRepository;


@Controller
@RequestMapping("/admin/inventory")
public class InventoryManagementController {

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private StockChangeLogRepository stockChangeLogRepository;

    @GetMapping("/index")
    public String list(Model model) {
        List<ProductStock> stocks = productStockRepository.findAll();
        model.addAttribute("stocks", stocks);
        return "admin/inventory/list";
    }

    @GetMapping("/adjust/{productId}")
    public String showAdjustForm(@PathVariable("productId") int productId, Model model) {
        ProductStock stock = productStockRepository.findByProductId(productId).get();
        AdjustStockForm adjustStockForm = new AdjustStockForm();
        adjustStockForm.setProductId(productId);

        model.addAttribute("stock", stock);
        model.addAttribute("adjustStockForm", adjustStockForm);

        return "admin/inventory/adjust";
    }

    @PostMapping("/adjust")
    public String adjustStock(AdjustStockForm adjustStockForm) {
        // 在庫情報の取得
        ProductStock productStock = productStockRepository.findByProductId(adjustStockForm.getProductId()).get();
        // 現在の在庫数を保持
        int previousQuantity = productStock.getQuantity();

        // 在庫数の調整
        int currentQuantity = previousQuantity + adjustStockForm.getAdjustmentQuantity();

        productStock.setQuantity(currentQuantity);
        productStockRepository.save(productStock);

        // 在庫変更ログの作成と保存
        StockChangeLog stockChangeLog = new StockChangeLog(
            productStock.getProduct(),
            previousQuantity,
            adjustStockForm.getAdjustmentQuantity(),
            currentQuantity,
            adjustStockForm.getReason());

        stockChangeLogRepository.save(stockChangeLog);

        return "admin/inventory/list";
    }
}
