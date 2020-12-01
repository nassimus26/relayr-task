package com.relayr.task.products.search.kafka.listners;

import com.relayr.task.common.dto.model.v1.SupplierRankDto;
import com.relayr.task.products.search.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.relayr.task.common.kafka.config.KafkaConst.SUPPLIER_RANK_TOPIC;

@RequiredArgsConstructor
@Component
@Slf4j
@KafkaListener(id = "suppliers_ranking", topics = SUPPLIER_RANK_TOPIC)
public class SupplierRankListener {
    private final SupplierService supplierService;

    @KafkaHandler
    public void onUpdateRanking(SupplierRankDto supplierRankDto) {
        log.debug("Receiving " + supplierRankDto);
        supplierService.updateSupplierRank(supplierRankDto.getId(), supplierRankDto.getRank());
    }
}
