package com.relayr.task.products.search.datasource.product.consumer;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.dto.mapper.SmartProductMapper;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.service.ProductService;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ProductBatchConsumer implements Consumer<List<ProductDto>> {
    private final SmartProductMapper productMapper;
    private final ProductService productService;

    @Override
    public void accept(List<ProductDto> productDtos) {
        List<Product> products = productMapper.map(productDtos);
        productService.saveAll(products);
        log.debug("Save the buffer, first record was : " + products.get(0));
    }
}
