package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateEmailCommand;
import com.ecommerce.bex.command.user.UpdateUsernameCommand;
import com.ecommerce.bex.service.command.UserCommandService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.USER_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class UserController implements UserAPI {

    private final UserCommandService userCommandService;

    @PutMapping(AppConstants.CHANGE_USERNAME_PATH)
    public ResponseEntity<Void> updateUsername(@RequestBody @Valid UpdateUsernameCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.CHANGE_EMAIL_PATH)
    public  ResponseEntity<Void> updateEmail(@RequestBody @Valid UpdateEmailCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }
}
