package com.learn.api.service.Inventory;

import java.util.List;
import java.util.Optional;

// import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.api.models.InventoryModel.BrandModel;
import com.learn.api.models.InventoryModel.DimensionModel;
import com.learn.api.models.InventoryModel.ItemModel;
import com.learn.api.models.InventoryModel.ManufacturersModel;
import com.learn.api.models.InventoryModel.PreferVendorModel;
import com.learn.api.models.InventoryModel.TypeWeightModel;
import com.learn.api.models.InventoryModel.UnitModel;
import com.learn.api.repositorys.InventoryRepository.BrandRepository;
import com.learn.api.repositorys.InventoryRepository.ItemRepository;
import com.learn.api.repositorys.InventoryRepository.ManufacturerModelRepository;
import com.learn.api.repositorys.InventoryRepository.PreferVendorRepository;
import com.learn.api.repositorys.InventoryRepository.TypeDimensionRepository;
import com.learn.api.repositorys.InventoryRepository.TypeWeightRepository;
import com.learn.api.repositorys.InventoryRepository.UnitRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TypeWeightRepository typeWeightRepository;

    @Autowired
    private TypeDimensionRepository typeDimensionRepository;

    @Autowired
    private ManufacturerModelRepository manufacturerModelRepository;

    @Autowired
    private PreferVendorRepository preferVendorRepository;

    public Page<ItemModel> getAllItems(Pageable pageable) {
        Page<ItemModel> items = itemRepository.findAll(pageable);
        // System.out.println("Items retrieved from database: " + items.size());
        return items;
    }

    public Optional<ItemModel> getItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<UnitModel> getAllUnit() {
        List<UnitModel> unitModels = unitRepository.findAll();
        return unitModels;
    }

    public List<BrandModel> getAllBrand() {
        List<BrandModel> brandModels = brandRepository.findAll();
        return brandModels;
    }

    public List<ManufacturersModel> getAllManufacturers() {
        List<ManufacturersModel> manufacturersModels = manufacturerModelRepository.findAll();
        return manufacturersModels;
    }

    public List<PreferVendorModel> getAllPreferVendor() {
        List<PreferVendorModel> preferVendorModels = preferVendorRepository.findAll();
        return preferVendorModels;
    }

    public List<TypeWeightModel> getTypeWeight() {
        List<TypeWeightModel> typeWeight = typeWeightRepository.findAll();
        return typeWeight;
    }

    public List<DimensionModel> getTypeDimension() {
        List<DimensionModel> typeDimentsion = typeDimensionRepository.findAll();
        return typeDimentsion;
    }
}
