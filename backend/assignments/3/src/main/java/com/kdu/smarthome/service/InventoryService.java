package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddInventoryRequest;
import com.kdu.smarthome.dto.response.AddInventoryResponse;
import com.kdu.smarthome.dto.response.ListAllDetailsResponse;
import com.kdu.smarthome.dto.response.ListInventoryResponse;
import com.kdu.smarthome.model.DeviceInventoryEntity;
import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Adds a new inventory item to the database.
     *
     * @param addInventoryRequestDTO The data transfer object containing inventory item details.
     * @return AddInventoryResponseDTO Response containing the added inventory item details.
     */
    public AddInventoryResponse addInventory(AddInventoryRequest addInventoryRequestDTO){

        // Assuming Mapper.getInventoryFromDTO correctly maps the DTO to the DeviceInventory entity
        DeviceInventoryEntity inventory = Mapper.getInventoryFromRequest(addInventoryRequestDTO);

        // Save the new inventory item
        DeviceInventoryEntity newInventory = inventoryRepository.save(inventory);

        return new AddInventoryResponse("Inventory added successfully!!", newInventory.toString(), HttpStatus.OK);
    }

    /**
     * Retrieves all inventory items from the database.
     *
     * @return GetAllInventoryResponseDTO Response containing all inventory items.
     */
    public ListAllDetailsResponse getAllInventory(){

        // Fetch all inventory items
        List<DeviceInventoryEntity> inventoryList = inventoryRepository.findAll();

        // Convert the list of inventory items to a string for the response
        String response = inventoryList.toString();

        return new ListAllDetailsResponse("All inventory items fetched successfully!!", response, HttpStatus.OK);
    }
}
