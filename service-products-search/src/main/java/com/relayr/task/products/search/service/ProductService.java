package com.relayr.task.products.search.service;


import com.relayr.task.common.dto.model.v1.ProductSupplierDto;
import com.relayr.task.products.search.model.Product;
import com.relayr.task.products.search.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.relayr.task.products.search.service.workaround.LookupField.fieldWithBrutePath;
import static org.springframework.data.mongodb.core.aggregation.Fields.field;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product save(Product product) {
        categoryService.save(product.getCategory());
        supplierService.saveAll(product.getSuppliers());
        return productRepository.save(product);
    }

    public List<Product> saveAll(Iterable<Product> prs) {
        List<Product> res = new ArrayList<>();
        prs.forEach(a -> res.add(save(a)));
        return res;
    }

    public List<ProductSupplierDto> findByNameAndCategoryName(String name, String category, Pageable pageable) {
        final List<AggregationOperation> operations = new ArrayList<>();
        MatchOperation matchNameStage = Aggregation.match(Criteria.where("name").is(name));
        LookupOperation lookupCategoryOperation = Aggregation.lookup(
                field("category"),
                fieldWithBrutePath("category.$id"),
                field("_id"),
                field("category"));
        MatchOperation matchCategoryNameStage = Aggregation.match(Criteria.where("category.name").is(category));
        UnwindOperation unwindCategoryStage = Aggregation.unwind("category");
        LookupOperation lookupSupplierOperation = Aggregation.lookup(
                field("supplier"),
                fieldWithBrutePath("suppliers.$id"),
                field("_id"),
                field("supplier"));
        UnwindOperation unwindSupplierStage = Aggregation.unwind("supplier");
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "supplier.rank");
        operations.add(matchNameStage);
        operations.add(lookupCategoryOperation);
        operations.add(matchCategoryNameStage);
        operations.add(unwindCategoryStage);
        operations.add(lookupSupplierOperation);
        operations.add(unwindSupplierStage);
        operations.add(sortOperation);

        if (pageable.isPaged()) {
            operations.add(Aggregation.skip((long) pageable.getPageNumber() * pageable.getPageSize()));
            operations.add(Aggregation.limit(pageable.getPageSize()));
        }

        AggregationResults<ProductSupplierDto> output
                = mongoTemplate.aggregate(Aggregation.newAggregation(operations), "product", ProductSupplierDto.class);
        return output.getMappedResults();
    }

}
