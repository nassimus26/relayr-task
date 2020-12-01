package com.relayr.task.supplier.ranking.base;

import com.relayr.task.base.test.RestIntegrationTest;
import com.relayr.task.supplier.ranking.ExcludeKafkaTestConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = ExcludeKafkaTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestIntegrationBaseTest extends RestIntegrationTest {

}
