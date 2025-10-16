package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateEmailCommand;
import com.ecommerce.bex.command.user.UpdateUsernameByIdCommand;
import com.ecommerce.bex.command.user.UpdateUsernameCommand;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.UserResponseDTO;
import com.ecommerce.bex.service.command.UserCommandService;
import com.ecommerce.bex.service.query.UserQueryService;
import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.USER_BASE_PATH)
@RequiredArgsConstructor
@PreAuthorize(AppConstants.PRE_AUTHORIZE_ALL_REQUISITION)
public class UserController implements UserAPI {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @GetMapping
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<PageResponseDTO<UserResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(userQueryService.findAll(pageable));
    }

    @PutMapping(AppConstants.CHANGE_USERNAME_PATH)
    public ResponseEntity<Void> updateUsername(@RequestBody UpdateUsernameCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.CHANGE_EMAIL_PATH)
    public  ResponseEntity<Void> updateEmail(@RequestBody UpdateEmailCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping(AppConstants.ADMIN_CHANGE_NAME_PATH)
    @PreAuthorize(AppConstants.PRE_AUTHORIZE_ADMIN_REQUISITION)
    public ResponseEntity<Void> updateUsernameById(@RequestBody @Valid UpdateUsernameByIdCommand command){
        userCommandService.update(command);
        return ResponseEntity.ok().build();
    }
}
