package com.example.Resturant.appuser;

public enum AppUserPermission {

    MENU_READ("menu:read"),
    MENU_WRITE("menu:write"),
    CATEGORIES_READ("categories:read"),
    CATEGORIES_WRITE("categories:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
