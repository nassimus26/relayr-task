package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Mapper(uses = {CategoryMapper.class, SupplierMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    Product map(ProductDto productDto);

    List<Product> map(List<ProductDto> productDtos);
}
