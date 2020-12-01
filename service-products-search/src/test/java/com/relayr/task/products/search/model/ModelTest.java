package com.relayr.task.products.search.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {
    @Test
    public void toStringTest() {// required to increase code coverage
        Supplier supplier = Supplier.builder().id("sup").name("sup_name").rank(10).build();
        Category category = new Category("cat", "cat_name");
        Product product = Product.builder().id("prd").name("pro_name").build();
        Assertions.assertThat(supplier.toString()).contains("sup_name");
        Assertions.assertThat(category.toString()).contains("cat_name");
        Assertions.assertThat(product.toString()).contains("pro_name");
    }
}
