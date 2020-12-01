package com.relayr.task.products.search.kafka.listners;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.datasource.product.ProductStreamBufferedDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.relayr.task.common.kafka.config.KafkaConst.PRODUCTS_TOPIC;
import static com.relayr.task.products.search.config.KafkaConsumerConfig.PRODUCTS_GROUP_ID;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProductsBatchListener {
    private final ProductStreamBufferedDataSource productBufferedDataSource;

    @KafkaListener(id = "products_listener", topics = PRODUCTS_TOPIC, groupId = PRODUCTS_GROUP_ID, containerFactory = "productsFactoryListener")
    public void onBatchReceived(List<ProductDto> products) {
        log.debug("Receiving products batch with size = " + products.size());
        productBufferedDataSource.push(products.stream());
    }
}
