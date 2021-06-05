package com.example.Resturant.order;

import com.example.Resturant.appuser.AppUser;
import com.example.Resturant.menu.Menu;
import com.example.Resturant.menu.MenuService;
import com.example.Resturant.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;
    private final AppUserService appUserService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable Long id) {
        Order order = orderService.findOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setOrderNumber(Math.abs(new Random().nextLong()));
        order.setOrderAt(LocalDateTime.now());
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updateOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{orderId}/menu/{menuId}")
    public ResponseEntity<Order> addMenuToOrder(@PathVariable Long orderId, @PathVariable Long menuId) {
        Order order = orderService.findOrder(orderId);
        Menu menu = menuService.findMenu(menuId);
        order.getMenus().add(menu);
        orderService.saveOrder(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/appUser/{appUserId}")
    public ResponseEntity<Order> addAppUserToOrder(@PathVariable Long orderId, @PathVariable Long appUserId) {
        Order order = orderService.findOrder(orderId);
        AppUser appUser = appUserService.findAppUser(appUserId);
        appUser.getOrders().add(order);
        order.setAppUser(appUser);
        orderService.saveOrder(order);

        orderService.sendVertificationEmail(appUser, order.getOrderNumber());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }



}
