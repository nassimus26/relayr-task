package com.relayr.task.supplier.ranking.restapi.v1;

import com.relayr.task.supplier.ranking.base.RestIntegrationBaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static com.relayr.task.RestApiConsts.SEND_RANK_MSG_OK;
import static com.relayr.task.RestApiConsts.SUPPLIERS_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class SupplierRankControllerTest extends RestIntegrationBaseTest {
    @MockBean
    private KafkaTemplate<Object, Object> template;

    @Test
    public void sendSupplierRankTest() {
        //@formatter:off
        given()
                .param("id", "syp_1")
                .param("rank", 10)
        .when().
                get(SUPPLIERS_URL + "/")
        .then().
                statusCode(HttpStatus.SC_OK).
                body(is(SEND_RANK_MSG_OK));
        //@formatter:on
    }
}
