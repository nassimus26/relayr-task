package com.relayr.task.products.search.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@CompoundIndex(def = "{'name':1, 'category.id': 1}", unique = true, name = "category_product")
public class Product {
    @Id
    private String id;

    @Indexed
    private String name;

    @DBRef
    private Category category;

    @DBRef
    @Builder.Default
    @Indexed
    private Set<Supplier> suppliers = new HashSet<>();
}
