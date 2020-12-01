package com.relayr.task.common.dto.data;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class RandomDataProviderTest {
    @Test
    public void buildDataWithOneSupplierTest() {
        List<ProductDto> products = RandomDataProvider.buildData(0, 1, 1, 1);
        assertThat(products).hasSize(1);
        ProductDto productDto = products.get(0);
        assertThat(productDto.getId()).isNotEmpty();
        assertThat(productDto.getName()).isNotEmpty();
        assertThat(productDto.getCategory()).isNotNull();
        assertThat(productDto.getSuppliers()).hasSize(1);
        SupplierDto supplierDto = productDto.getSuppliers().stream().findFirst().get();
    }

    @Test
    public void buildDataWithManySupplierTest() {
        List<ProductDto> products = RandomDataProvider.buildData(0, 1, 100, 1);
        assertThat(products).hasSize(1);
        ProductDto productDto = products.get(0);
        assertThat(productDto.getId()).isNotEmpty();
        assertThat(productDto.getName()).isNotEmpty();
        assertThat(productDto.getCategory()).isNotNull();
        assertThat(productDto.getSuppliers().size()).isGreaterThan(1);
    }
}
