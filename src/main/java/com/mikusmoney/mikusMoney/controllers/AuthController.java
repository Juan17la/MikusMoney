package com.mikusmoney.mikusMoney.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikusmoney.mikusMoney.dto.AuthResponse;
import com.mikusmoney.mikusMoney.dto.LoginRequest;
import com.mikusmoney.mikusMoney.dto.MikuCreateRequest;
import com.mikusmoney.mikusMoney.dto.MikuResponse;
import com.mikusmoney.mikusMoney.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/v0.1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("register")
    public ResponseEntity<AuthResponse> registerUser(
        @RequestBody MikuCreateRequest mikuCreateRequest,
        HttpServletResponse response
    ) {
        AuthResponse authResponse = authService.signUp(mikuCreateRequest);
        setAuthCookie(response, authResponse.getToken());
        
        return ResponseEntity.ok(AuthResponse.builder()
                .message("Registration successful")
                .build());
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> loginUser(
        @RequestBody LoginRequest loginRequest,
        HttpServletResponse response
    ) {    
        AuthResponse authResponse = authService.login(loginRequest.getEmail(), loginRequest.getPinCode());
        setAuthCookie(response, authResponse.getToken());
        
        return ResponseEntity.ok(AuthResponse.builder()
                .message("Login successful")
                .build());
    }
    
    @PostMapping("logout")
    public ResponseEntity<AuthResponse> logout(HttpServletResponse response) {
        clearAuthCookie(response);
        return ResponseEntity.ok(AuthResponse.builder()
                .message("Logout successful")
                .build());
    }
    
    @GetMapping("me")
    public ResponseEntity<MikuResponse> me() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }
    

    /**
     * Sets the JWT token as an HTTP-only cookie
     * @param response HttpServletResponse to add the cookie
     * @param token JWT token to store in the cookie
     */
    private void setAuthCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("AUTH-TOKEN", token);
        cookie.setHttpOnly(true); // Prevents JavaScript access (XSS protection)
        cookie.setSecure(false); // Set to true in production with HTTPS
        cookie.setPath("/"); // Cookie available for all paths
        cookie.setMaxAge(24 * 60 * 60); // 24 hours in seconds
        // cookie.setSameSite("Strict"); // CSRF protection (requires Spring 6+)
        response.addCookie(cookie);
    }
    
    /**
     * Clears the authentication cookie by setting its max age to 0
     * @param response HttpServletResponse to clear the cookie
     */
    private void clearAuthCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Set to true in production with HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0); // Delete cookie
        response.addCookie(cookie);
    }

}
