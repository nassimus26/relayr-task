package com.relayr.task.supplier.ranking.restapi.v1;

import com.relayr.task.common.dto.model.v1.SupplierRankDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.relayr.task.RestApiConsts.*;
import static com.relayr.task.common.kafka.config.KafkaConst.SUPPLIER_RANK_TOPIC;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@RestController
@RequestMapping(SUPPLIERS_URL)
@Api(RELAYR_TASK_API)
@RequiredArgsConstructor
public class SupplierRankController {

    private final KafkaTemplate<Object, Object> template;

    /*
     * Handle GET /api/v1/suppliers/?id=${sup_id}&rank={new_rank}
     * */
    @GetMapping("/")
    public ResponseEntity<String> send(@RequestParam("id") String supId, @RequestParam("rank") int rank) {
        try {
            template.send(SUPPLIER_RANK_TOPIC, UUID.randomUUID().toString(), new SupplierRankDto(supId, rank));
            return ResponseEntity.ok(SEND_RANK_MSG_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the message");
        }
    }

}
