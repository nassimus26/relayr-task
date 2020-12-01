package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.model.Supplier;
import com.relayr.task.products.search.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Aim to fix cyclic reference Product <-> Supplier and making sure that suppliers have unique references
 * <p>
 * @author Nassim MOUALEK
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SmartProductMapper {
    private final ProductMapper productMapper;
    private final SupplierService supplierService;

    public List<Product> map(List<ProductDto> productDtos) {
        Map<String, Supplier> suppliers = new HashMap<>();
        return productDtos.stream().map(a -> map_(a, suppliers)).collect(Collectors.toList());
    }

    public Product map(ProductDto productDto) {
        return map_(productDto, new HashMap<>());
    }

    private Product map_(ProductDto productDto, Map<String, Supplier> uniqueRefSuppliers) {
        Product product = productMapper.map(productDto);
        Set<Supplier> suppliers = new HashSet<>();
        product.getSuppliers().forEach(sup -> {
            Supplier supplier = uniqueRefSuppliers.getOrDefault(sup.getId(), supplierService.findById(sup.getId()).orElse(sup));
            uniqueRefSuppliers.put(supplier.getId(), supplier);
            supplier.getProducts().add(product);
            suppliers.add(supplier);
        });
        product.setSuppliers(suppliers);
        return product;
    }
}

