package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.products.search.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-04T19:26:06+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Product map(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setCategory( categoryMapper.map( productDto.getCategory() ) );
        product.setSuppliers( supplierMapper.map( productDto.getSuppliers() ) );

        return product;
    }

    @Override
    public List<Product> map(List<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            list.add( map( productDto ) );
        }

        return list;
    }
}
