package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.SupplierDto;
import com.relayr.task.products.search.model.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SupplierMapper {
    Supplier map(SupplierDto supplierDto);

    List<Supplier> map(List<SupplierDto> supplierDtos);

    Set<Supplier> map(Set<SupplierDto> supplierDtos);
}
