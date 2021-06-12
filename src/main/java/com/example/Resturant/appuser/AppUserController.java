package com.example.Resturant.appuser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/appUser")
public class AppUserController {

    private String set;
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    /*
    @GetMapping("/customers")
    public ResponseEntity<List<AppUser>> findAllCustomers() {
        List<AppUser> appUsers = appUserService.findAllCustomers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<AppUser> findCustomer(@PathVariable("id") Long id) {
        AppUser appUser = appUserService.findCustomer(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createAppUser(@RequestBody AppUser appUser) {
        AppUser newAppUser = appUserService.createAppUser(appUser);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    @PutMapping("/customers/update")
    public ResponseEntity<AppUser> updatePerson(@RequestBody AppUser person) {
        AppUser updateAppUser = appUserService.updateCustomer(person);
        return new ResponseEntity<>(updateAppUser, HttpStatus.OK);
    }


    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        appUserService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */




}
