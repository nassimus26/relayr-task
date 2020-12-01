package com.relayr.task.data.provider.base;

import com.relayr.task.base.test.RestIntegrationTest;
import com.relayr.task.data.provider.ExcludeKafkaTestConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = ExcludeKafkaTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestIntegrationBaseTest extends RestIntegrationTest {

}
