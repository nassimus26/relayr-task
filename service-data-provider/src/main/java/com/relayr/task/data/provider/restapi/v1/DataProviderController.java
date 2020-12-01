package com.relayr.task.data.provider.restapi.v1;

import com.relayr.task.common.dto.data.RandomDataProvider;
import com.relayr.task.common.dto.model.v1.ProductDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.relayr.task.RestApiConsts.*;
import static com.relayr.task.common.kafka.config.KafkaConst.PRODUCTS_TOPIC;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@RestController
@RequestMapping(SEND_BATCH_URL)
@Api(RELAYR_TASK_API)
public class DataProviderController {
    @Autowired
    private KafkaTemplate<String, List> template;

    /**
     * Handle GET /api/v1/data/?offset=${offset}
     * @param offset a number between 0 to 100, allows to return a different batch
     * */
    @GetMapping("/")
    public ResponseEntity<String> send(@RequestParam("offset") int offset) {
        if (offset<0 || offset>100)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Offset must be between 0 and 10");
        try {
            List<ProductDto> productDtos = RandomDataProvider.buildData(offset, 10, 120, 200);
            template.send(PRODUCTS_TOPIC, UUID.randomUUID().toString(), productDtos);
            return ResponseEntity.ok().body(SEND_DATA_MSG_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the message");
        }
    }

}
