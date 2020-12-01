package com.relayr.task.products.search.service;

import com.relayr.task.products.search.model.Supplier;
import com.relayr.task.products.search.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void saveAll(Iterable<Supplier> supplier) {
        supplierRepository.saveAll(supplier);
    }

    public Optional<Supplier> findById(String id) {
        return supplierRepository.findById(id);
    }

    public Supplier updateSupplierRank(String id, int rank) {
        if (rank < 0 || rank > 10)
            throw new RuntimeException("Rank must be between 0 and 10");
        Supplier supplier = findById(id).orElse(null);
        if (supplier == null)
            throw new RuntimeException("Supplier with id " + id + " not founds");
        supplier.setRank(rank);
        return save(supplier);
    }
}
