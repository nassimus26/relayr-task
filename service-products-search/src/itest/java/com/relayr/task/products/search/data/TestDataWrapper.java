package com.relayr.task.products.search.data;

import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.model.Supplier;
import lombok.Value;

import java.util.List;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Value
public class TestDataWrapper {
    List<Product> products;
    List<Category> categories;
    List<Supplier> suppliers;
}
