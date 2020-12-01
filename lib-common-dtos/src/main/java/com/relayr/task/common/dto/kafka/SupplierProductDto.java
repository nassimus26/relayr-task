package com.relayr.task.common.dto.kafka;

import com.relayr.task.common.dto.model.v1.CategoryDto;
import lombok.Data;
import lombok.ToString;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Data
public class SupplierProductDto {
    String id;
    String name;
    int rank;
    SubProductDto product;
}

@ToString
class SubProductDto {
    String id;
    String name;
    CategoryDto category;
};
