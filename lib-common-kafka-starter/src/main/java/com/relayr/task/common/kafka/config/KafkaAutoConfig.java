package com.relayr.task.common.kafka.config;

import com.relayr.task.common.dto.model.v1.SupplierRankDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

import static com.relayr.task.common.kafka.config.KafkaConst.PRODUCTS_TOPIC;
import static com.relayr.task.common.kafka.config.KafkaConst.SUPPLIER_RANK_TOPIC;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Configuration
public class KafkaAutoConfig {
    @Bean
    public RecordMessageConverter converter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages(SupplierRankDto.class.getPackage().getName());
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put(SUPPLIER_RANK_TOPIC, SupplierRankDto.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public NewTopic supplierRankTopic() {
        return TopicBuilder.name(SUPPLIER_RANK_TOPIC)
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic productsBatchTopic() {
        return TopicBuilder.name(PRODUCTS_TOPIC)
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }
}
