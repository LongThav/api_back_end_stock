package com.learn.api.serviceImpl.SaleIServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.api.config.DuplicateInvoiceNumberException;
import com.learn.api.constants.Enum.SaleEnum.InvoiceStatueEnum;
import com.learn.api.dto.SaleDTO.SaleOrderDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.AddPackageLamBokDTO;
import com.learn.api.lombokDTO.SaleLomBokDTO.PackageLamBokDTO;
import com.learn.api.models.InventoryModel.ItemModel;
import com.learn.api.models.SaleModel.CustomerModel;
import com.learn.api.models.SaleModel.PackageModel;
import com.learn.api.models.SaleModel.SaleOrderModel;
import com.learn.api.repositorys.InventoryRepository.ItemRepository;
import com.learn.api.repositorys.SaleRepository.CustomerRepository;
import com.learn.api.repositorys.SaleRepository.PackageRepository;
import com.learn.api.repositorys.SaleRepository.SaleOrderRepository;
import com.learn.api.service.SaleService.SaleOrderService.SaleOrderService;
import com.learn.api.utility.Mapper.SaleMapper.PackageMapper;
import com.learn.api.utility.Mapper.SaleMapper.SaleOrderMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SaleOrderImpl implements SaleOrderService {

        @Autowired
        private SaleOrderRepository saleOrderRepository;

        @Autowired
        private CustomerRepository customerRepository;

        @Autowired
        private ItemRepository itemRepository;

        @Autowired
        private PackageRepository packageRepository;

        // Query Sale Order
        @Override
        public Page<SaleOrderDTO> getSaleOrder(Pageable pageable) {
                Page<SaleOrderModel> saleOrder = saleOrderRepository.findAll(pageable);
                // Force initialization of proxies
                saleOrder.forEach(invoice -> invoice.getCustomer().getCustomerId());
                List<SaleOrderDTO> saleOrderDTOs = saleOrder.stream()
                                .map(SaleOrderMapper::convertSaleOrderToDTO)
                                .collect(Collectors.toList());
                return new PageImpl<>(saleOrderDTOs, pageable, saleOrder.getTotalElements());
        }

        @Override
        @Transactional
        public SaleOrderModel addSaleOrder(Long customerId, Long itemId, SaleOrderModel saleOrderModel) {
                // Check if the retainer_invoice_number already exists
                Optional<SaleOrderModel> existingSaleOrder = saleOrderRepository
                                .findBysalesOrderNumber(saleOrderModel.getSalesOrderNumber());
                if (existingSaleOrder.isPresent()) {
                        throw new DuplicateInvoiceNumberException("The sale order is already in use.");
                }
                // item
                ItemModel item = itemRepository.findById(itemId)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Item not found with id: " + saleOrderModel
                                                                                .getItemInventory().getItemID()));
                // saleOrderModel.setItemInvenotory(item);

                // Proceed with saving the new invoice
                CustomerModel customer = customerRepository.findById(customerId)
                                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

                saleOrderModel.setCustomer(customer);
                saleOrderModel.setItemInvenotory(item);
                saleOrderModel.setStatus(InvoiceStatueEnum.Pending.toString()); // Ensure this value is valid

                return saleOrderRepository.save(saleOrderModel);
        }

        // get package
        @Override
        public Page<PackageLamBokDTO> getAllPackages(Pageable pageable) {
                Page<PackageModel> packagePage = packageRepository.findAll(pageable);
                // Force initialization of proxies if needed
                packagePage.forEach(packageModel -> packageModel.getItemId()); // Adjust based on your use case
                packagePage.forEach(packageModel -> packageModel.getCustomerId());
                packagePage.forEach(packageModel -> packageModel.getSaleOrderId());

                List<PackageLamBokDTO> packageDTOs = packagePage.stream()
                                .map(PackageMapper::convertPackageToDTO)
                                .collect(Collectors.toList());

                return new PageImpl<>(packageDTOs, pageable, packagePage.getTotalElements());
        }

        // add package
        @Override
        public PackageModel addPackage(AddPackageLamBokDTO packageLamBokDTO) {
                // Convert DTO to entity
                PackageModel packageModel = PackageMapper.convertToEntity(packageLamBokDTO);

                // Validate customer, sale order, and item
                validateReferences(packageModel);

                // Save the entity using the repository
                return packageRepository.save(packageModel);
        }

        private void validateReferences(PackageModel packageModel) {
                // Check if customer exists
                if (packageModel.getCustomerId() == 0 || packageModel.getCustomerId() == null) {
                        throw new EntityNotFoundException(
                                        "Customer with ID " + packageModel.getCustomerId() + " not found.");
                }

                // Check if sale order exists
                if (packageModel.getSaleOrderId() == 0 || packageModel.getSaleOrderId() == null) {
                        throw new EntityNotFoundException(
                                        "Sale Order with ID " + packageModel.getSaleOrderId() + " not found.");
                }

                // Check if item exists
                if (packageModel.getItemId() == 0 || packageModel.getItemId() == null) {
                        throw new EntityNotFoundException("Item with ID " + packageModel.getItemId() + " not found.");
                }
        }
}
