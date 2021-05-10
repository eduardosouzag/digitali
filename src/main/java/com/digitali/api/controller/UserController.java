package com.digitali.api.controller;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.digitali.api.entity.User;
import com.digitali.api.repository.UserRepository;
import com.digitali.api.service.UserService;

//Verificar se existe conteudo no pacote e direciona para a camada de negócio
@RestController("/user")
public class UserController {
    public static String DEFAULT_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static String[] ADMIN_ACESS = {"ROLE_ADMIN", "ROLE_MODERATOR"};
    public static String[] MODERATOR_ACESS = {"ROLE_MODERATOR"};
    
    @Autowired
    protected UserRepository repository;

    @Autowired
    protected UserService service;


    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user) {
        try {
            if (Objects.nonNull(user)){
                ResponseEntity.ok(service.save(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @GetMapping("/deactivated/{userId}")
    public ResponseEntity<String> deactivated(@PathVariable int userId) {
        try {
            repository.deactivated(userId);
            return ResponseEntity.ok("Usuário desativado!");
        } catch (Exception e) {
            //return new ResponseStatusException(HttpStatus.FORBIDDEN, "Não foi possível desativar o usuário!");
        }
        
        return null;
    }
        
    @PostMapping("/join")
    public ResponseEntity<User> join(@RequestBody User user) {
        try {
            user.setRoles(DEFAULT_USER);
            repository.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    //Se estiver logado com  ADMIN OU MODERADOR
    @GetMapping("/access/{userId}/{userRole}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal){
        Optional<User> userOpt = repository.findById(userId);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            List<String> activeRoles = getRolesByLoggedInUser(principal);

            if (activeRoles.contains(userRole)){
                String newRole = user.getRoles()+","+userRole;
                user.setRoles(newRole);
            }

            repository.save(user);
            return "Bem vindo "+ user.getUserName()+ " Nova permissão associada " + principal.getName();
        }

        return null;
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
        if (Objects.nonNull(principal) && Objects.nonNull(principal.getName())){
            Optional<User> userOptional = repository.findByUserName(principal.getName());
            if (userOptional.isPresent()){
                return userOptional.get();
            }
        }
        return null;
    }
    
    private List<String> getRolesByLoggedInUser(Principal principal){
        if (Objects.nonNull(principal)){
            User user = getLoggedInUser(principal);
            if (Objects.nonNull(user)){
                String roles = user.getRoles();
                List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
                if (assignRoles.contains(ROLE_ADMIN)){
                    return Arrays.stream(ADMIN_ACESS).collect(Collectors.toList());
                }

                if (assignRoles.contains(ROLE_MODERATOR)){
                    return Arrays.stream(MODERATOR_ACESS).collect(Collectors.toList());
                }
            }
        }

        return Collections.emptyList();
    } 
}
