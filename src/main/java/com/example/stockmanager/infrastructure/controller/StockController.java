package com.example.stockmanager.infrastructure.controller;


import com.example.stockmanager.application.dto.TransferRequestDto;
import com.example.stockmanager.application.service.ProductServiceImpl;
import com.example.stockmanager.application.service.StockServiceImpl;
import com.example.stockmanager.domain.model.MovementType;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final ProductServiceImpl productService;

    private final StockServiceImpl stockService;

    public StockController(ProductServiceImpl productService, StockServiceImpl stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }


    @PutMapping("/entrada/{sku}")
    public ResponseEntity<String> updateStockAvailable(@PathVariable("sku") String sku , @RequestParam("quantity") long quantity ) {
        try {
            Product product = productService.findProductBySku(sku);

            System.out.println("valor recebido do front " + quantity);
            stockService.stockEntry(product , quantity);
            return ResponseEntity.ok("Entrada de " + quantity  + " para " +product.getSku() +" bem sucedida");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<String > transferStock(@RequestBody TransferRequestDto transfer){
        try{
            Product product = productService.findProductBySku(transfer.getSku());

            stockService.transferStock(product , transfer.getQuantity() , transfer.getFromStockType() , transfer.getToStockType());

            return ResponseEntity.ok("Transferência de estoque realizada com sucesso.");

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
