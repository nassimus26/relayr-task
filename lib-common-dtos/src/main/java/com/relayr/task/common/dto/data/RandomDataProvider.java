package com.relayr.task.common.dto.data;

import com.relayr.task.common.dto.model.v1.CategoryDto;
import com.relayr.task.common.dto.model.v1.ProductDto;
import com.relayr.task.common.dto.model.v1.SupplierDto;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class RandomDataProvider {
    public static final int MAX_RANK = 10;
    private static final Random random = new Random();

    public static ProductDto buildOne(int offset, int nbrCategories, int nbrSuppliers) {
        return buildData(offset, nbrCategories, nbrSuppliers, 1).get(0);
    }

    public static List<ProductDto> buildData(int offset, int nbrCategories, int nbrSuppliers, int nbrProducts) {
        List<CategoryDto> categories = IntStream.range(0, nbrCategories).mapToObj(i ->
                new CategoryDto(buildID(CategoryDto.class, i + offset * nbrCategories), buildName(CategoryDto.class, (i + offset * nbrCategories)))).collect(Collectors.toList());

        List<SupplierDto> suppliers = IntStream.range(0, nbrSuppliers).mapToObj(i ->
                new SupplierDto(buildID(SupplierDto.class, i + offset * nbrSuppliers), buildName(SupplierDto.class, (i + offset * nbrSuppliers)), random.nextInt(MAX_RANK))).collect(Collectors.toList());

        return IntStream.range(0, nbrProducts).mapToObj(i -> {
                    int minSupplierCount = Math.max(suppliers.size() / 5, 1);
                    int from = random.nextInt(Math.max(suppliers.size() - minSupplierCount, 1));
                    List<SupplierDto> subSuppliers = suppliers.subList(from, Math.min(from + minSupplierCount, suppliers.size()));
                    return new ProductDto(buildID(ProductDto.class, i + offset * nbrProducts),
                            buildName(ProductDto.class, (i + offset * nbrProducts)),
                            categories.get(i % categories.size()), new HashSet<>(subSuppliers));
                }
        ).collect(Collectors.toList());
    }

    public static String buildID(Class type, int id) {
        return type.getSimpleName().substring(0, 3).toLowerCase() + "_" + id;
    }

    public static String buildName(Class type, int id) {
        return type.getSimpleName().substring(0, 3).toLowerCase() + "_" + "name" + "_" + id;
    }

}
