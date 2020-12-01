package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.CategoryDto;
import com.relayr.task.products.search.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category map(CategoryDto categoryDto);

    List<Category> map(List<CategoryDto> categoryDtos);
}
