package Projects.BarbellBenders.controller;

import Projects.BarbellBenders.dto.LoginRequest;
import Projects.BarbellBenders.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import Projects.BarbellBenders.dto.RegisterRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new account")
    @PostMapping("/register")
    public Map<String, String> register(@Valid @RequestBody RegisterRequest request) {
        String result = userService.register(request.getUsername(), request.getPassword(), request.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return response;
    }

    @Operation(summary = "Login to an existing account")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
