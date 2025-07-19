package com.inventorymanagement.controller;

import com.inventorymanagement.entity.Inventory;
import com.inventorymanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Create Inventory
    @PostMapping
    public ResponseEntity<Inventory> postInventory(@RequestBody Inventory inventory) {
        Inventory saved = inventoryService.createInventory(inventory);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get All Inventories
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> list = inventoryService.getAllInventories();
        return list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Get Inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    // Update Inventory
    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        Inventory updated = inventoryService.updateInventory(inventory);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete Inventory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
