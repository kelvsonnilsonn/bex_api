package com.ecommerce.bex.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstants {

    public static final String USER_BASE_PATH = "/users";
    public static final String AUTH_BASE_PATH = "/auth";
    public static final String PRODUCT_BASE_PATH = "/product";
    public static final String CART_BASE_PATH = "/cart";
    public static final String ORDER_BASE_PATH = "/order";
    public static final String EVENT_BASE_PATH = "/events";
    public static final String ADMIN_PATH = "/admin";

    public static final String ID_PATH = "/{id}";
    public static final String CATEGORY_SEARCH_PATH = "/category/{productCategory}";
    public static final String UPDATE_NAME_PATH = "/name";
    public static final String UPDATE_PRICE_PATH = "/price";
    public static final String UPDATE_STOCK_PATH = "/stock";
    public static final String ALL_DATA_SEARCH_PATH = "/all";

    public static final String EVENTS_IN_INTERVAL_PATH = "/interval";
    public static final String MY_EVENTS_IN_INTERVAL_PATH = "/my-events/interval";
    public static final String USER_EVENTS_IN_INTERVAL_PATH = "/user-events/interval";

    public static final String USER_EVENT_SEARCH_PATH = "/user/{userId}";
    public static final String AGGREGATE_EVENT_SEARCH_PATH = "/aggregate/{aggregateId}";
    public static final String AGGREGATE_EVENT_BY_TYPE_SEARCH_PATH = "/aggregate/{aggregateType}";

    public static final String UPDATE_STATUS_PATH = "/upstatus";

    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";

    public static final String CHANGE_PASSWORD_PATH = "/change-password";
    public static final String CHANGE_USERNAME_PATH = "/change-name";
    public static final String CHANGE_EMAIL_PATH = "/change-email";

    public static final String PRE_AUTHORIZE_ADMIN_REQUISITION = "hasAuthority('ADMIN_ROLE')";
    public static final String PRE_AUTHORIZE_ALL_REQUISITION = "permitAll()";
    public static final String PRE_AUTHORIZE_SELLER_REQUISITION = "hasAuthority('SELLER_ROLE')";

    public static final Long SYSTEM_ID = 0L;
    public static final String AGGREGATE_ORDER_TYPE = "ORDER";
    public static final String AGGREGATE_CART_TYPE = "CART";
    public static final String AGGREGATE_PRODUCT_TYPE = "PRODUCT";
}
