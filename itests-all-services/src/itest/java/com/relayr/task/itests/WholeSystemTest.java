package com.relayr.task.itests;

import com.relayr.task.RestApiConsts;
import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.common.dto.model.v1.ProductSupplierDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static com.relayr.task.RestApiConsts.*;
import static com.relayr.task.common.dto.data.RandomDataProvider.MAX_RANK;
import static com.relayr.task.common.dto.data.RandomDataProvider.buildOne;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = BasicTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WholeSystemTest {
    @Value("${relayr.products_search.port}")
    int productsSearchServicePort;
    @Value("${relayr.suppliers_ranking.port}")
    int suppliersRankingServicePort;
    @Value("${relayr.data_provider.port}")
    int dataProviderServicePort;

    @Test
    public void fullSystemTest() {
        resetDataBase();
        sleep(3000);
        ProductDto productDto = buildOne(0, 1, 1);
        int pageSize = 10;
        List<ProductSupplierDto> productDtos = findProductByNameAndCategoryName(productDto, pageSize);
        assertThat(productDtos).isEmpty();// no data for the moment
        triggerBatchDataProviderService();
        sleep(10000);
        productDto = buildOne(0, 1, 1);
        productDtos = findProductByNameAndCategoryName(productDto, pageSize);
        assertThat(productDtos).hasSize(pageSize);
        ensureProductsAreSortedBySupplierRank(pageSize, productDtos);
        ProductSupplierDto worstRankedProduct = productDtos.get(productDtos.size() - 1);
        triggerUpdateSupplierRankService(worstRankedProduct.getSupplier().getId(), MAX_RANK);
        sleep(3000);
        List<ProductSupplierDto> resortedProductDtos = findProductByNameAndCategoryName(productDto, pageSize);
        ensureProductsAreSortedBySupplierRank(pageSize, resortedProductDtos);// it should still sorted
        ensureThatTheWorstProductBecomeAtTheTop(worstRankedProduct, resortedProductDtos);
    }

    private void ensureThatTheWorstProductBecomeAtTheTop(ProductSupplierDto worstRankedProduct, List<ProductSupplierDto> resortedProductDtos) {
        for (ProductSupplierDto first : resortedProductDtos) {
            if (first.getId().equals(worstRankedProduct.getId()))
                break;
            assertThat(first.getSupplier().getRank()).isEqualTo(MAX_RANK);
        }
    }

    private void ensureProductsAreSortedBySupplierRank(int nbrSupplier, List<ProductSupplierDto> productDtos) {
        assertThat(productDtos).hasSize(nbrSupplier);
        for (int i = 0; i < productDtos.size() - 1; i++) {
            SupplierDto productSupplier_i = productDtos.get(i).getSupplier();
            SupplierDto productSupplier_i_1 = productDtos.get(i + 1).getSupplier();
            assertThat(productSupplier_i.getRank()).isGreaterThanOrEqualTo(productSupplier_i_1.getRank());
        }
    }

    private List<ProductSupplierDto> findProductByNameAndCategoryName(ProductDto productDto, int pageSize) {
        setRestAssurePort(productsSearchServicePort);
        //@formatter:off
        return Arrays.asList(given()
                .param("name", productDto.getName())
                .param("category", productDto.getCategory().getName())
                .param("page", 0)
                .param("size", pageSize)
                .when()
                .get(RestApiConsts.PRODUCTS_URL + "/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(ProductSupplierDto[].class));
        //@formatter:on
    }

    public void triggerUpdateSupplierRankService(String supId, int rank) {
        setRestAssurePort(suppliersRankingServicePort);
        //@formatter:off
        given()
                .param("id", supId)
                .param("rank", rank)
                .when()
                .get(SUPPLIERS_URL + "/")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(SEND_RANK_MSG_OK));
        //@formatter:on
    }

    private void setRestAssurePort(int port) {
        RestAssured.port = port;
    }

    private void triggerBatchDataProviderService() {
        setRestAssurePort(dataProviderServicePort);
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

    private void resetDataBase() {
        for (int i=1;i<=3;i++) { // give it more chances to succeed
            try {
                setRestAssurePort(productsSearchServicePort);
                //@formatter:off
                given()
                .when()
                        .get(RestApiConsts.PRODUCTS_URL + "/reset/")
                .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body(is(RESET_MSG_OK));
                //@formatter:on
                break;
            } catch (Exception e) {
                sleep(10000); // give the services some times to startup
            }
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
