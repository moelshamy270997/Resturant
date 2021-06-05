package com.example.Resturant.categories;

import com.example.Resturant.menu.Menu;
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
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categoriesSet")
    private Set<Menu> menus = new HashSet<>();

}
