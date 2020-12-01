package com.relayr.task.data.provider.restapi.v1;

import com.relayr.task.data.provider.base.RestIntegrationBaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static com.relayr.task.RestApiConsts.SEND_BATCH_URL;
import static com.relayr.task.RestApiConsts.SEND_DATA_MSG_OK;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class DataProviderControllerTest extends RestIntegrationBaseTest {
    @MockBean
    private KafkaTemplate<Object, Object> template;

    @Test
    public void sendBatchControllerTest() {
        //@formatter:off
        given()
                .param("offset", 0)
        .when()
                .get(SEND_BATCH_URL + "/")
                .then()
                .statusCode(HttpStatus.SC_OK)
        .body(is(SEND_DATA_MSG_OK));
        //@formatter:on
    }
}
