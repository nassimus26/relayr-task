package com.relayr.task.common.dto.data;

import com.relayr.task.common.dto.model.v1.CategoryDto;
import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;
import com.relayr.task.common.dto.model.v1.SupplierRankDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class DtoTest {

    @Test
    public void toStringTest() {// required to increase code coverage
        SupplierRankDto supplierRankDto = new SupplierRankDto("sup_id", 10);
        ProductDto productDto = new ProductDto("pro_id", "",
                new CategoryDto("cat_id", "name"),
                new HashSet<>());
        SupplierDto supplierDto = new SupplierDto("sup_id", "", 0);

        Assertions.assertThat(supplierRankDto.toString()).contains("sup_id");
        Assertions.assertThat(productDto.toString()).contains("pro_id");
        Assertions.assertThat(productDto.getCategory().toString()).contains("cat_id");
        Assertions.assertThat(supplierDto.toString()).contains("sup_id");
    }
}
