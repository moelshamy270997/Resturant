package com.example.Resturant.registration;

import com.example.Resturant.appuser.AppUserRole;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String phone;
    private final String email;
    private final String city;
    private final String street;
    private final AppUserRole appUserRole;
}
