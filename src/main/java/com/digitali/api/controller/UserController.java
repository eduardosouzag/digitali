package com.digitali.api.controller;

import com.digitali.api.entity.User;
import com.digitali.api.repository.UserRepository;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.digitali.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Verificar se existe conteudo no pacote e direciona para a camada de negócio
@RestController("/user")
public class UserController {
    public static String DEFAULT_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static String[] ADMIN_ACESS = {"ROLE_ADMIN", "ROLE_MODERATOR"};
    public static String[] MODERATOR_ACESS = {"ROLE_MODERATOR"};
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user) {
        try {
            if (Objects.nonNull(user)){
                ResponseEntity.ok(service.save(user));
            }
        } catch (Exception e) {

        }
        
        return null;
    }
    
    @GetMapping("/deactivated/{userId}")
    public ResponseEntity deactivated(@PathVariable int userId) {
        try {
            repository.deactivated(userId);
            return ResponseEntity.ok("Usuário desativado!");
        } catch (Exception e) {
            //return new ResponseStatusException(HttpStatus.FORBIDDEN, "Não foi possível desativar o usuário!");
        }
        
        return null;
    }
        
    @PostMapping("/join")
    public ResponseEntity<User> joinGroup(@RequestBody User user) {
        try {
            user.setRoles(DEFAULT_USER);
//            String encryptedPwd = secu.encode(user.getPassword());
//            user.setPassword(encryptedPwd);
            repository.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
        }
        
        return null;
    }

    //Se estiver logado com  ADMIN OU MODERADOR
    @GetMapping("/access/{userId}/{userRole}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal){
       User user = repository.findById(userId).get();
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        String newRole ="";
        
        if (activeRoles.contains(userRole)){
            newRole = user.getRoles()+","+userRole;
            user.setRoles(newRole);
        }
        
        repository.save(user);
        return "Bem vindo "+ user.getUserName()+ " Nova permissão associada " + principal.getName();
    }
    
    @GetMapping
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> loadUsers(){
        return repository.findAll();
    }
    
    @GetMapping("/test")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> testUserAccess(){
        return repository.findAll();
    }
    
    private User getLoggedInUser(Principal principal){
        return repository.findByUserName(principal.getName()).get();
    }
    
    private List<String> getRolesByLoggedInUser(Principal principal){
        String roles = getLoggedInUser(principal).getRoles();
        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignRoles.contains(ROLE_ADMIN)){
            return Arrays.stream(ADMIN_ACESS).collect(Collectors.toList());
        }
        
        if (assignRoles.contains(ROLE_MODERATOR)){
            return Arrays.stream(MODERATOR_ACESS).collect(Collectors.toList());
        }
        
        return Collections.emptyList();
    } 
}
