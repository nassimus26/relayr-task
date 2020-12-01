package com.relayr.task.products.search.repository;

import com.relayr.task.products.search.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public interface CategoryRepository extends MongoRepository<Category, String> {

}
