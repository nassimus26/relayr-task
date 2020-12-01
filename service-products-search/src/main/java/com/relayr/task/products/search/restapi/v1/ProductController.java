package com.relayr.task.products.search.restapi.v1;

import com.relayr.task.common.dto.model.v1.ProductSupplierDto;
import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.model.Supplier;
import com.relayr.task.products.search.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.relayr.task.RestApiConsts.*;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Value
@RestController
@RequestMapping(PRODUCTS_URL)
@Api(RELAYR_TASK_API)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;
    MongoTemplate mongoTemplate;

    /*
     * Handle GET /api/v1/products/?name=${product_name}&category=${category_name}&page=${page}&size=${size}
     * */
    @GetMapping("/")
    public List<ProductSupplierDto> find(@RequestParam("name") String name, @RequestParam("category") String category,
                                         @RequestParam("page") int page, @RequestParam("size") int size) {
        return productService.findByNameAndCategoryName(name, category, PageRequest.of(page, size));
    }

    /*
     * Handle GET /api/v1/products/reset/
     * */
    @GetMapping("/reset/")
    public ResponseEntity<String> reset() {
        try {
            mongoTemplate.dropCollection(Product.class);
            mongoTemplate.dropCollection(Supplier.class);
            mongoTemplate.dropCollection(Category.class);
            return ResponseEntity.ok(RESET_MSG_OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Rest Failed");
        }
    }

}
