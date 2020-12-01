package com.relayr.task.products.search.restapi.v1;

import com.relayr.task.RestApiConsts;
import com.relayr.task.common.dto.model.v1.ProductSupplierDto;
import com.relayr.task.products.search.base.DbIntegrationBaseTest;
import com.relayr.task.products.search.data.TestDataProvider;
import com.relayr.task.products.search.data.TestDataWrapper;
import com.relayr.task.products.search.model.Product;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class ProductControllerTest extends DbIntegrationBaseTest {

    @Test
    public void findProductsTest() {
        int nbrSupplier = 6;
        TestDataWrapper data = TestDataProvider.buildData(nbrSupplier, 2, 4);
        persistData(data);
        Product product = data.getProducts().get(0);
        //@formatter:off
        ProductSupplierDto[] productDtos =
                given()
                        .param("name", product.getName())
                        .param("category", product.getCategory().getName())
                        .param("page", 0)
                        .param("size", 10)
                .when()
                        .get(RestApiConsts.PRODUCTS_URL + "/")
                .then()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(ContentType.JSON)
                        .extract().as(ProductSupplierDto[].class);
        //@formatter:on
        assertThat(productDtos).hasSize(nbrSupplier);
    }

    @Test
    public void resetDataTest() {
        //@formatter:off
        given()
        .when()
                .get(RestApiConsts.PRODUCTS_URL + "/reset/")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(RestApiConsts.RESET_MSG_OK));
        //@formatter:on
    }
}
