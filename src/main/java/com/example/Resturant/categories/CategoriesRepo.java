package com.example.Resturant.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {

    Optional<Categories> findCategorysById(Long id);
}
