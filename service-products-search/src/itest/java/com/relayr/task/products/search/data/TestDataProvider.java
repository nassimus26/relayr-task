package com.relayr.task.products.search.data;

import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.relayr.task.common.dto.data.RandomDataProvider.buildID;
import static com.relayr.task.common.dto.data.RandomDataProvider.buildName;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class TestDataProvider {
    public static TestDataWrapper buildData(int nbrSupplier, int nbrCategory, int nbrProduct) {
        List<Supplier> suppliers = IntStream.range(0, nbrSupplier).boxed().map(i ->
                Supplier.builder()
                        .id(buildID(Supplier.class, i))
                        .name(buildName(Supplier.class, i))
                        .rank(8 - i)
                        .build())
                .collect(Collectors.toList());
        List<Category> categories = IntStream.range(0, nbrCategory).boxed().map(i ->
                new Category(buildID(Category.class, (i % 2)),
                            buildName(Category.class, (i % 2))))
                .collect(Collectors.toList());

        List<Product> products = IntStream.range(0, nbrProduct).boxed().map(i -> {
            Product p = Product.builder()
                    .id(buildID(Product.class, i))
                    .name(buildName(Product.class, i))
                    .category(categories.get(i % 2)).build();
            suppliers.forEach(s -> {
                s.getProducts().add(p);
                p.getSuppliers().add(s);
            });
            return p;
        }).collect(Collectors.toList());
        return new TestDataWrapper(products, categories, suppliers);
    }
}
