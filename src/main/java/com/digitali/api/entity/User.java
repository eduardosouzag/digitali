package com.digitali.api.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "USER")
public class User implements Serializable {

   @Id
   @GeneratedValue
   private int id;
   
   @Column(name = "username", nullable = false, unique = true)
   private String userName;
   
   @Column(name = "password", nullable = false)
   private String password;
   
   @Column(name="email", nullable = true)
   private String email;
   
   @Column(name="name", nullable = false)
   private String name;
   
   @Column(name="cellphone", nullable = true)
   private String cellphone;
   
   @Column(name="roles")
   private String roles;
   
   @Column(name="active")
   private boolean active;

   @Version
   private Long version;
}