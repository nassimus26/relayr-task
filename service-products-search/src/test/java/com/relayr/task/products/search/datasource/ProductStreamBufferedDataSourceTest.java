package com.relayr.task.products.search.datasource;

import com.relayr.task.common.dto.data.RandomDataProvider;
import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.datasource.product.ProductStreamBufferedDataSource;
import io.reactivex.rxjava3.functions.Consumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductStreamBufferedDataSourceTest {
    @InjectMocks
    private ProductStreamBufferedDataSource productStreamBufferedDataSource;
    @Mock
    private Consumer<List<ProductDto>> consumer;

    @Test
    public void productStreamBufferedDataSourceTest() throws Throwable {
        List<ProductDto> productDtos = RandomDataProvider.buildData(0, 10, 100, 200);
        productStreamBufferedDataSource.push(productDtos.stream());
        int expectedNumberOfConsumerCall = productDtos.size() / ProductStreamBufferedDataSource.BUFFER_SIZE;
        BDDMockito.verify(consumer, times(expectedNumberOfConsumerCall)).accept(any());
        BDDMockito.verifyNoMoreInteractions(consumer);
    }
}
