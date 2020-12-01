package com.relayr.task.products.search.datasource.product;

import com.relayr.task.common.dto.model.v1.ProductDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@RequiredArgsConstructor
@Component
public class ProductStreamBufferedDataSource {
    public static final int BUFFER_SIZE = 50;
    private final Consumer<List<ProductDto>> consumer;

    public void push(Stream<ProductDto> data) {
        Flowable.fromStream(data).buffer(BUFFER_SIZE).subscribe(consumer);
    }

}
