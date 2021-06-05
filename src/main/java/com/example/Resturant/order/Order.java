package com.example.Resturant.order;

import com.example.Resturant.appuser.AppUser;
import com.example.Resturant.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(unique = true, updatable = false)
    private Long orderNumber;
    private LocalDateTime orderAt;

    private Double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "order_menu",
            joinColumns = {
                @JoinColumn(name = "order_id", referencedColumnName = "id",
                        nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "menu_id", referencedColumnName = "id",
                            nullable = false, updatable = false)}
    )
    private Set<Menu> menus = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


}
