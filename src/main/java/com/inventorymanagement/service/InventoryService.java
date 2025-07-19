package com.inventorymanagement.service;

import com.inventorymanagement.entity.Inventory;
import com.inventorymanagement.exception.InventoryNotFoundException;
import com.inventorymanagement.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll().stream().toList();
    }

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Inventory inventory) {
        Inventory existing = inventoryRepository.findById(inventory.getId())
                .orElseThrow(() -> new InventoryNotFoundException("Inventory with ID " + inventory.getId() + " not found"));

        existing.setName(inventory.getName());
        existing.setQuantity(inventory.getQuantity());
        existing.setPrice(inventory.getPrice());

        return inventoryRepository.save(existing);
    }

    public void deleteInventory(Integer id) {
        if (!inventoryRepository.existsById(id)) {
            throw new InventoryNotFoundException("Inventory with ID " + id + " not found");
        }
        inventoryRepository.deleteById(id);
    }

    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory with ID " + id + " not found"));
    }
}
