package com.studmed.user.user.interfaces.rest;

import com.studmed.user.shared.exception.BadRequestException;
import com.studmed.user.user.domain.model.aggregates.User;
import com.studmed.user.user.domain.model.commands.CreateUserCommand;
import com.studmed.user.user.domain.model.commands.DeleteUserCommand;
import com.studmed.user.user.domain.model.commands.UpdateUserCommand;
import com.studmed.user.user.domain.model.queries.GetAllUserQuery;
import com.studmed.user.user.domain.model.queries.GetUserByIdQuery;
import com.studmed.user.user.domain.model.queries.GetUserByEmail;
import com.studmed.user.user.domain.service.UserCommandService;
import com.studmed.user.user.domain.service.UserQueryService;
import com.studmed.user.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.studmed.user.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.studmed.user.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.studmed.user.user.interfaces.rest.resource.CreateUserResource;
import com.studmed.user.user.interfaces.rest.resource.UpdateUserResource;
import com.studmed.user.user.interfaces.rest.resource.UserResource;
import com.studmed.user.user.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "User", description = "User Management Endpoints")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    private final JwtUtil jwtUtil;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService, JwtUtil jwtUtil) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody @Valid CreateUserResource createUserResource) {
        CreateUserCommand createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        Long id = userCommandService.handle(createUserCommand);

        User user = userQueryService.handle(new GetUserByIdQuery(id));

        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResource);
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<String> login(@PathVariable String email, @PathVariable String password) {
        User user = userQueryService.handle(new GetUserByEmail(email, password));

        String tokenString = jwtUtil.generateToken(user);

        return ResponseEntity.ok(tokenString);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<User> user = userQueryService.handle(new GetAllUserQuery());

        List<UserResource> userResource = user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        User user = userQueryService.handle(new GetUserByIdQuery(id));

        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserResource updateUserResource) {
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser mayor que 0");
        }

        UpdateUserCommand updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(id, updateUserResource);
        Long userId = userCommandService.handle(updateUserCommand);

        User user = userQueryService.handle(new GetUserByIdQuery(userId));

        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserCommand);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myObject")
    public ResponseEntity<UserResource> getUserByToken(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userQueryService.handle(new GetUserByIdQuery(userId));

        UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }
}