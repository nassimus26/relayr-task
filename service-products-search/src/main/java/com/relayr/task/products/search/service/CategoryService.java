package com.relayr.task.products.search.service;

import com.relayr.task.products.search.model.Category;
import com.relayr.task.products.search.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> saveAll(Iterable<Category> cats) {
        return categoryRepository.saveAll(cats);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
