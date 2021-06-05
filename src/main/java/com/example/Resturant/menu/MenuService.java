package com.example.Resturant.menu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepo menuRepo;


    public List<Menu> findAllMenus() {
        return menuRepo.findAll();
    }

    public Menu createMenu(Menu menu) {
        return menuRepo.save(menu);
    }

    public Menu findMenu(Long id) {
        return menuRepo.findMenuById(id)
                .orElseThrow(() -> new IllegalStateException("Menu not Found"));
    }

    public Menu updateMenu(Menu menu) {
        return menuRepo.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepo.deleteById(id);
    }
}
