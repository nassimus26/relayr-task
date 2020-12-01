package com.relayr.task.data.provider;

import com.relayr.task.common.kafka.config.KafkaAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(KafkaAutoConfig.class)
@SpringBootApplication
public class DataProviderBoot {

    public static void main(String[] args) {
        SpringApplication.run(DataProviderBoot.class, args);
    }

}
