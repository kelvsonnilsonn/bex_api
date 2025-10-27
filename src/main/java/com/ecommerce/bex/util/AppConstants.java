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

    public static final String UPDATE_NAME_PATH = "/name";
    public static final String UPDATE_PRICE_PATH = "/price";
    public static final String UPDATE_STOCK_PATH = "/stock";

    public static final String EVENTS_IN_INTERVAL_PATH = "/interval";
    public static final String MY_EVENTS_IN_INTERVAL_PATH = "/my-events/interval";
    public static final String USER_EVENTS_IN_INTERVAL_PATH = "/user-events/interval";

    public static final String UPDATE_STATUS_PATH = "/upstatus";

    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";

    public static final String CHANGE_PASSWORD_PATH = "/change-password";
    public static final String CHANGE_USERNAME_PATH = "/change-name";
    public static final String CHANGE_EMAIL_PATH = "/change-email";
    public static final String CHANGE_ADDRESS_PATH = "/change-address";

    public static final String PRE_AUTHORIZE_ADMIN_REQUISITION = "hasAuthority('ADMIN_ROLE')";
    public static final String PRE_AUTHORIZE_ALL_REQUISITION = "permitAll()";
    public static final String PRE_AUTHORIZE_SELLER_REQUISITION = "hasAuthority('SELLER_ROLE')";

    public static final Long SYSTEM_ID = 0L;
    public static final String AGGREGATE_ORDER_TYPE = "ORDER";
    public static final String AGGREGATE_CART_TYPE = "CART";
    public static final String AGGREGATE_PRODUCT_TYPE = "PRODUCT";

    public static final String PRODUCT_ID_NN_MESSAGE = "O ID do produto é obrigatório";
    public static final String PRODUCT_PRICE_POSITIVE_MESSAGE = "O preço do produto deve ser maior que 0!";
    public static final String PRODUCT_STOCK_POSITIVE_MESSAGE = "Informar a quantidade do produto no estoque é obrigatório";

    public static final String USER_ID_NN_MESSAGE = "O ID do usuário é obrigatório";

    public static final String USERNAME_NOT_NULL_MESSAGE = "O username é obrigatório";
    public static final String EMAIL_NOT_NULL_MESSAGE = "O Email é obrigatório";
    public static final String PASSWORD_NOT_NULL_MESSAGE = "A Senha é obrigatória";
    public static final String POSITIVE_ADDRESS_NUMBER_MESSAGE = "O número da residência deve ser positivo maior que zero.";

}
