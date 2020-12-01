package com.relayr.task.supplier.ranking;

import com.relayr.task.common.kafka.config.KafkaAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@SpringBootApplication(exclude = {KafkaAutoConfig.class, KafkaAutoConfiguration.class})
public class ExcludeKafkaTestConfig {

    public static void main(String[] args) {
        SpringApplication.run(ExcludeKafkaTestConfig.class, args);
    }

}
