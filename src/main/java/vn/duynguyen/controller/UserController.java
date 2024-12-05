package vn.duynguyen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.duynguyen.dto.request.UserRequestDTO;
import vn.duynguyen.dto.response.PageResponse;
import vn.duynguyen.dto.response.ResponseData;
import vn.duynguyen.dto.response.ResponseError;
import vn.duynguyen.service.UserService;
import vn.duynguyen.util.UserStatus;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Add new user", description = "API create new user")
    @PostMapping("/")
    public ResponseData<String> addUser(@Valid @RequestBody UserRequestDTO user) {
        log.info("Adding user: {}", user);
        try {
            String userId = userService.saveUser(user);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully", userId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add user failed!");
        }
    }

    @Operation(summary = "Get user data", description = "API get user data")
    @GetMapping("/{userId}")
    public ResponseData<String> getUser(@PathVariable("userId") String userId) {
        log.info("Get userId: {}", userId);
        try {
            userService.getUser(userId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get user successfully", userId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get user failed!");
        }
    }

    @Operation(summary = "Update user status", description = "API update user status")
    @PostMapping("/{userId}")
    public ResponseData<String> changeUserStatus(@PathVariable("userId") String userId, @Valid @RequestBody UserStatus status) {
        log.info("Change status userId: {}", userId);
        try {
            userService.changeStatus(userId, status);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully", userId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update user failed!");
        }
    }

    @Operation(summary = "Update user data", description = "API update user data")
    @PutMapping("/{userId}")
    public ResponseData<String> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserRequestDTO user) {
        log.info("Update user: {}", user);
        try {
            userService.updateUser(userId, user);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully", userId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update user failed!");
        }
    }

    @Operation(summary = "Delete user", description = "API delete user")
    @DeleteMapping("/{userId}")
    public ResponseData<String> deleteUser(@PathVariable("userId") String userId) {
        log.info("Delete userId: {}", userId);
        try {
            userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User deleted successfully", userId);
        } catch (Exception e) {
            log.error("errorMessage = {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete user failed!");
        }
    }

    @Operation(summary = "Get user list per page", description = "Return user by pageNo and pageSize")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PageResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") @Min(10) int pageSize) {
        log.info("Request getAllUsers");
        return new ResponseData<>(HttpStatus.OK.value(), "users", userService.getAllUsers(pageNo, pageSize));
    }
}
