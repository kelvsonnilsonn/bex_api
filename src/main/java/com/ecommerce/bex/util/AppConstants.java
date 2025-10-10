package com.ecommerce.bex.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static final String USER_BASE_PATH = "/users";
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String PRODUCT_BASE_PATH = "/product";
    public static final String CART_BASE_PATH = "/cart";
    public static final String ORDER_BASE_PATH = "/order";

    public static final String ID_PATH = "/{id}";
    public static final String CATEGORY_SEARCH_PATH = "/category/{productCategory}";
    public static final String ITEMS_SEARCH_PATH = "/items";
    public static final String ALL_DATA_SEARCH_PATH = "/all";

    public static final String UPDATE_STATUS_PATH = "/upstatus";

    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";

    public static final String PRE_AUTHORIZE_ADMIN_REQUISITION = "hasRole('ADMIN_ROLE')";
    public static final String PRE_AUTHORIZE_ALL_REQUISITION = "permitAll()";
    public static final String PRE_AUTHORIZE_SELLER_REQUISITION = "hasRole('SELLER_ROLE')";
}
