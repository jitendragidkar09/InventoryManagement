package com.inventorymanagement.controller;

import com.inventorymanagement.entity.Inventory;
import com.inventorymanagement.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public Inventory postInventory(@RequestBody Inventory inventory){
        return inventoryService.createInventory(inventory);
    }

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory(){
        return inventoryService.getAllInventories();
    }

    @PutMapping("/inventory")
    public Inventory updateInventory(@RequestBody Inventory inventory){
        return inventoryService.updateInventory(inventory);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") Integer id){
        inventoryService.deleteInventory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
