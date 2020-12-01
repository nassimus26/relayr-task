package com.relayr.task.products.search.service.workaround;

import org.springframework.data.mongodb.core.aggregation.Field;

public class LookupField {
    public static Field fieldWithBrutePath(String path) {
        return new Field() { // Workaround of Spring Data Bug, unable to send aggregation with field.$id path
            @Override
            public String getName() {
                return path;
            }

            @Override
            public String getTarget() {
                return path;
            }

            @Override
            public boolean isAliased() {
                return false;
            }
        };
    }
}
