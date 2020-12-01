package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.CategoryDto;
import com.relayr.task.products.search.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-04T19:26:06+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category map(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }

    @Override
    public List<Category> map(List<CategoryDto> categoryDtos) {
        if ( categoryDtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDtos.size() );
        for ( CategoryDto categoryDto : categoryDtos ) {
            list.add( map( categoryDto ) );
        }

        return list;
    }
}
