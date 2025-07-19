package com.inventorymanagement.service;

import com.inventorymanagement.entity.Inventory;
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

    public Optional<Inventory> updateInventory(Inventory inventory) {
        return inventoryRepository.findById(inventory.getId()).map(existing -> {
            existing.setName(inventory.getName());
            existing.setPrice(inventory.getPrice());
            existing.setQuantity(inventory.getQuantity());
            return inventoryRepository.save(existing);
        });
    }

    public boolean deleteInventory(Integer id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Inventory> getInventoryById(Integer id) {
        return inventoryRepository.findById(id);
    }
}
