package com.relayr.task.products.search.dto.mapper;

import com.relayr.task.common.dto.model.v1.SupplierDto;
import com.relayr.task.products.search.model.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-04T19:26:06+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.5 (JetBrains s.r.o)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier map(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( supplierDto.getId() );
        supplier.setName( supplierDto.getName() );
        supplier.setRank( supplierDto.getRank() );

        return supplier;
    }

    @Override
    public List<Supplier> map(List<SupplierDto> supplierDtos) {
        if ( supplierDtos == null ) {
            return null;
        }

        List<Supplier> list = new ArrayList<Supplier>( supplierDtos.size() );
        for ( SupplierDto supplierDto : supplierDtos ) {
            list.add( map( supplierDto ) );
        }

        return list;
    }

    @Override
    public Set<Supplier> map(Set<SupplierDto> supplierDtos) {
        if ( supplierDtos == null ) {
            return null;
        }

        Set<Supplier> set = new HashSet<Supplier>( Math.max( (int) ( supplierDtos.size() / .75f ) + 1, 16 ) );
        for ( SupplierDto supplierDto : supplierDtos ) {
            set.add( map( supplierDto ) );
        }

        return set;
    }
}
