package com.relayr.task.products.search.datasource.product;

import com.relayr.task.common.dto.model.v1.ProductDto;
import io.reactivex.rxjava3.core.Flowable;
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
public class ProductPushDataSource {

    private final Consumer<ProductDto> consumer;

    public void push(ProductDto productDto) {
        Flowable.just(productDto).subscribe(consumer);
    }

}
