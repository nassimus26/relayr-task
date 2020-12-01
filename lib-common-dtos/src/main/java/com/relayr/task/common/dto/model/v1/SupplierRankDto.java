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
public class SupplierRankDto {
    private String id;
    private int rank;
}
