package com.inventorymanagement.service;

import com.inventorymanagement.entity.Inventory;
import com.inventorymanagement.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    //To get all the inventories
    public List<Inventory> getAllInventories(){
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList;
    }

    //To create new inventory
    @Transactional
    public Inventory createInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    //To update an inventory
    public Inventory updateInventory(Inventory inventory){
        Inventory oldInventory = inventoryRepository.findById(inventory.getId()).get();
        oldInventory.setName(inventory.getName());
        oldInventory.setPrice(inventory.getPrice());
        oldInventory.setQuantity(inventory.getQuantity());
        return oldInventory;
    }

    //To delete an inventory
    public void deleteInventory(Integer id){
        inventoryRepository.deleteById(id);
    }
}
