package com.ecommerce.bex.command.user;

import com.ecommerce.bex.validation.ValidZipcode;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.Positive;

public record UpdateUserAddressCommand(
        String street,
        String city,
        String neighborhood,
        String country,
        @Positive(message = AppConstants.POSITIVE_ADDRESS_NUMBER_MESSAGE) int number,
        @ValidZipcode String zipcode
) {
}
