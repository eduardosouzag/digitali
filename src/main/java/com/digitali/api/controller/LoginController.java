package com.digitali.api.controller;

import com.digitali.api.entity.User;
import com.digitali.api.entity.request.AuthRequest;
import com.digitali.api.service.UserService;
import com.digitali.api.util.AuthenticationSecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationSecurityUtil authenticationSecurityUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService customerUserDetailsService;

    @PostMapping("/logon")
    public ResponseEntity<User> logon(@RequestBody AuthRequest authRequest) {
        try {
            User user = customerUserDetailsService.getUser(authRequest.getUserName());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
        }
        
        return null;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("usuário e senha inválidos");
        }
        return authenticationSecurityUtil.generateToken(authRequest.getUserName());
    }
    
    @GetMapping("/")
    public String logout() {
        return "";
    }
}
