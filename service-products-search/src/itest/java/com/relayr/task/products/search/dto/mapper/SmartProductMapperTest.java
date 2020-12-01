package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.data.RandomDataProvider;
import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;
import com.relayr.task.products.search.base.DbIntegrationBaseTest;
import com.relayr.task.products.search.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class SmartProductMapperTest extends DbIntegrationBaseTest {
    @Autowired
    private SmartProductMapper smartProductMapper;

    private List<ProductDto> productDtos;

    @BeforeAll
    public void setUp() {
        productDtos = RandomDataProvider.buildData(0, 2, 5, 1);
    }

    @Test
    public void mapProductDtoTest() {
        ProductDto productDto = productDtos.get(0);
        Product product = smartProductMapper.map(productDto);
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getCategory()).isNotNull();
        assertThat(product.getCategory().getId()).isEqualTo(productDto.getCategory().getId());
        assertThat(product.getCategory().getName()).isEqualTo(productDto.getCategory().getName());
        assertThat(product.getSuppliers()).isNotNull();
        assertThat(product.getSuppliers()).hasSameSizeAs(productDto.getSuppliers());
        Set<SupplierDto> supplierDtos = product.getSuppliers().stream()
                .map(s -> new SupplierDto(s.getId(), s.getName(), s.getRank()))
                .collect(Collectors.toSet());
        assertThat(supplierDtos)
                .containsExactlyInAnyOrderElementsOf(productDto.getSuppliers());
    }

}
