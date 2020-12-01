package com.relayr.task.products.search.repository;

import com.relayr.task.products.search.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameAndCategoryName(String name, String category, Pageable pageable);

}
