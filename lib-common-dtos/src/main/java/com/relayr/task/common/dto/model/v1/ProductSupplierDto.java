package com.relayr.task.common.dto.model.v1;

import lombok.Data;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Data
public class ProductSupplierDto {
    private String id;
    private String name;
    private CategoryDto category;
    private SupplierDto supplier;
}

