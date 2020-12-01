package com.relayr.task.products.search.service;

import com.relayr.task.common.dto.model.v1.ProductSupplierDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;
import com.relayr.task.products.search.base.DbIntegrationBaseTest;
import com.relayr.task.products.search.data.TestDataProvider;
import com.relayr.task.products.search.data.TestDataWrapper;
import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.relayr.task.common.dto.data.RandomDataProvider.buildName;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class ProductServiceTest extends DbIntegrationBaseTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;

    @Test
    public void findProductByNameAndCategory() {
        int nbrSupplier = 5;
        int nbrCategory = 2;
        int nbrProduct = 4;
        TestDataWrapper data = TestDataProvider.buildData(nbrSupplier, nbrCategory, nbrProduct);

        persistData(data);

        List<ProductSupplierDto> productDtos = productService.findByNameAndCategoryName(
                buildName(Product.class, 1), buildName(Category.class, 1), Pageable.unpaged());
        ensureProductsAreSortedBySupplierRank(nbrSupplier, productDtos);
    }

    private void ensureProductsAreSortedBySupplierRank(int nbrSupplier, List<ProductSupplierDto> productDtos) {
        assertThat(productDtos).hasSize(nbrSupplier);
        for (int i = 0; i < productDtos.size() - 1; i++) {
            SupplierDto productSupplier_i = productDtos.get(i).getSupplier();
            SupplierDto productSupplier_i_1 = productDtos.get(i + 1).getSupplier();
            assertThat(productSupplier_i.getRank()).isGreaterThanOrEqualTo(productSupplier_i_1.getRank());
        }
    }


}
