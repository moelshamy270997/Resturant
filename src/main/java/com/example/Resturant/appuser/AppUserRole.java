package com.example.Resturant.appuser;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Resturant.appuser.AppUserPermission.*;

@Getter
public enum AppUserRole {
    USER(Sets.newHashSet(MENU_READ, CATEGORIES_READ)),
    ADMIN(Sets.newHashSet(MENU_READ, MENU_WRITE, CATEGORIES_READ, CATEGORIES_WRITE)),
    CUSTOMER(Sets.newHashSet(MENU_READ, CATEGORIES_READ));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
