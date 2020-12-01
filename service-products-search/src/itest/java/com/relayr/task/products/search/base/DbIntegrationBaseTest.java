package com.relayr.task.products.search.base;

import com.relayr.task.base.test.RestIntegrationTest;
import com.relayr.task.products.search.ExcludeKafkaTestConfig;
import com.relayr.task.products.search.data.TestDataWrapper;
import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.model.Supplier;
import com.relayr.task.products.search.service.CategoryService;
import com.relayr.task.products.search.service.ProductService;
import com.relayr.task.products.search.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = ExcludeKafkaTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DbIntegrationBaseTest extends RestIntegrationTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void clearData() {
        mongoTemplate.dropCollection(Supplier.class);
        mongoTemplate.dropCollection(Category.class);
        mongoTemplate.dropCollection(Product.class);
    }

    protected void persistData(TestDataWrapper data) {
        categoryService.saveAll(data.getCategories());
        supplierService.saveAll(data.getSuppliers());
        productService.saveAll(data.getProducts());
    }
}
