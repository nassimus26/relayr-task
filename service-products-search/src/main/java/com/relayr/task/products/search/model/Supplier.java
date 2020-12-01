package com.relayr.task.products.search.model;

import lombok.*;
import org.springframework.data.annotation.Id;
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
@ToString(exclude = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Supplier {
    @Id
    private String id;

    @Indexed
    private String name;

    private String type;

    @Indexed
    private int rank;

    @DBRef(lazy = true)
    @Builder.Default
    private Set<Product> products = new HashSet<>();
}
