package com.example.Resturant.menu;

import com.example.Resturant.categories.Categories;
import com.example.Resturant.categories.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/menu")
public class MenuController {

    private final MenuService menuService;
    private final CategoriesService categoriesService;

    @GetMapping("/all")
    public ResponseEntity<List<Menu>> getMenu() {
        List<Menu> menus = menuService.findAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findMenu(@PathVariable Long id) {
        Menu menu = menuService.findMenu(id);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('menu:write')")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu newMenu = menuService.createMenu(menu);
        return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('menu:write')")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu) {
        Menu updateMenu = menuService.updateMenu(menu);
        return new ResponseEntity<>(updateMenu, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('menu:write')")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{menuId}/categories/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Menu> addCategoryToMenu(@PathVariable Long menuId, @PathVariable Long categoryId) {
        Menu menu = menuService.findMenu(menuId);
        Categories category = categoriesService.findCategory(categoryId);

        menu.getCategoriesSet().add(category);
        menuService.updateMenu(menu);

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

}
