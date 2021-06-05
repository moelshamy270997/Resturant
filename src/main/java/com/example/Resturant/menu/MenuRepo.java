package com.example.Resturant.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepo extends JpaRepository<Menu, Long> {

    Optional<Menu> findMenuById(Long id);
}
