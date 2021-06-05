package com.example.Resturant.menu;

import com.example.Resturant.categories.Categories;
import com.example.Resturant.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double calories;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "menu_categories",
            joinColumns = {
                    @JoinColumn(name = "menu_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "categories_id", referencedColumnName = "id",
                            nullable = false, updatable = false)}
    )
    private Set<Categories> categoriesSet = new HashSet<>();


    public Menu(String name, String description, Double price, Double calories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
    }
}
