package com.example.Resturant.categories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService {

    private final CategoriesRepo categoriesRepo;


    public List<Categories> findAllCategories() {
        return categoriesRepo.findAll();
    }

    public Categories createCategory(Categories category) {
        return categoriesRepo.save(category);
    }

    public Categories findCategory(Long id) {
        return categoriesRepo.findCategorysById(id)
                .orElseThrow(() -> new IllegalStateException("Category not Found"));
    }

    public Categories updateCategory(Categories category) {
        return categoriesRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoriesRepo.deleteById(id);
    }
}
