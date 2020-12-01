package com.relayr.task.common.dto.model.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private String id;
    private String name;
    private int rank;
}
