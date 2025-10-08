package com.ecommerce.bex.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static final String USER_BASE_PATH = "/users";
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String PRODUCT_BASE_PATH = "/product";

    public static final String ID_PATH = "/{id}";
    public static final String CATEGORY_SEARCH_PATH = "/category/{productCategory}";

    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";
}
