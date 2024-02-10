package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddInventoryRequest;
import com.kdu.smarthome.dto.response.AddInventoryResponse;
import com.kdu.smarthome.dto.response.ListAllDetailsResponse;
import com.kdu.smarthome.service.InventoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    /**
     * Adds an inventory item.
     *
     * @param addInventoryRequestDTO The request DTO for adding an inventory item.
     * @return A ResponseEntity with the added inventory response DTO.
     */
    @PostMapping
    public ResponseEntity<AddInventoryResponse> addInventory(@Valid @RequestBody AddInventoryRequest addInventoryRequestDTO) {
        log.info("POST: /api/v1/inventory");
        log.debug("Request data: {}", addInventoryRequestDTO);

        AddInventoryResponse addInventoryResponseDTO = inventoryService.addInventory(addInventoryRequestDTO);
        return new ResponseEntity<>(addInventoryResponseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all inventory items.
     *
     * @return A ResponseEntity with all inventory response DTO.
     */
    @GetMapping
    public ResponseEntity<ListAllDetailsResponse> getAllInventory() {
        log.info("GET: /api/v1/inventory");

        ListAllDetailsResponse getAllInventoryResponseDTO = inventoryService.getAllInventory();
        return new ResponseEntity<>(getAllInventoryResponseDTO, HttpStatus.OK);
    }
}
