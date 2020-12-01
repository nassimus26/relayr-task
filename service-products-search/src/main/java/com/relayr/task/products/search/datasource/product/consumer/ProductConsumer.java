package com.relayr.task.products.search.datasource.product.consumer;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.dto.mapper.SmartProductMapper;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.service.ProductService;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@RequiredArgsConstructor
@Component
public class ProductConsumer implements Consumer<ProductDto> {
    private final SmartProductMapper productMapper;
    private final ProductService productService;

    @Override
    public void accept(ProductDto productDto) {
        Product product = productMapper.map(productDto);
        productService.save(product);
    }
}
