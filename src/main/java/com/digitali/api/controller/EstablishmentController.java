package com.digitali.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitali.api.entity.Establishment;
import com.digitali.api.entity.request.EstablishmentRequest;
import com.digitali.api.service.EstablishmentService;

@RestController
@RequestMapping({ "/establishment" })
public class EstablishmentController {
    @Autowired
    private EstablishmentService service;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to digitali !!";
    }

    @PostMapping("/new")
    public ResponseEntity save(@Validated @RequestBody EstablishmentRequest establishmentRequest) throws Exception{
        try {

            Establishment bean = new Establishment();
            BeanUtils.copyProperties(bean, establishmentRequest);
            return ResponseEntity.ok(service.save(bean));
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Establishment>> list() throws Exception{
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
