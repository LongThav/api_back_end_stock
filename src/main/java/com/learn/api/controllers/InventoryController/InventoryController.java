package com.learn.api.controllers.InventoryController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.models.InventoryModel.BrandModel;
import com.learn.api.models.InventoryModel.DimensionModel;
import com.learn.api.models.InventoryModel.ItemModel;
import com.learn.api.models.InventoryModel.ManufacturersModel;
import com.learn.api.models.InventoryModel.TypeWeightModel;
import com.learn.api.models.InventoryModel.UnitModel;
import com.learn.api.service.Inventory.ItemService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAllUsers(
            HttpServletRequest httpRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Check if the token is missing
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }

        // Create a Pageable object
        Pageable pageable = PageRequest.of(page, size);

        // Retrieve a paginated list of items
        Page<ItemModel> itemsPage = itemService.getAllItems(pageable);

        // Check if there are no items on the requested page
        if (itemsPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(404, "No items found", itemsPage));
        }

        // Return the paginated response
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response item successfully", itemsPage));
    }

    @GetMapping("/test-items-direct")
    public ResponseEntity<List<Map<String, Object>>> testItemsDirect() {
        String sql = "SELECT Name, SKU, Type FROM tbl_Items LIMIT 10";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(results);
    }

    // Endpoint to get an item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemModel> getItemById(@PathVariable("id") Long id) {
        Optional<ItemModel> item = itemService.getItemById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/unit")
    public ResponseEntity<?> getUnit() {
        List<UnitModel> unit = itemService.getAllUnit();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response unit successfully", unit));
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getBrand() {
        List<BrandModel> unit = itemService.getAllBrand();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response brand successfully", unit));
    }

    @GetMapping("/manufacturer")
    public ResponseEntity<?> manufacturers() {
        List<ManufacturersModel> manufacturers = itemService.getAllManufacturers();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response manufacturer successfully", manufacturers));
    }

    @GetMapping("/type-weight")
    public ResponseEntity<?> typeWeight() {
        List<TypeWeightModel> typeWeight = itemService.getTypeWeight();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response type weight successfully", typeWeight));
    }

    @GetMapping("/type-dimentsion")
    public ResponseEntity<?> typeDimention() {
        List<DimensionModel> dimensionModels = itemService.getTypeDimension();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response dimension successfully", dimensionModels));
    }
}