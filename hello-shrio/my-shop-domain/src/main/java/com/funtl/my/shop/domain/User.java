package com.funtl.my.shop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
 private String email;
 private String password;
 private String username;
 private  Long id;
 private String phone;
 private Date created;
 private Date update;

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }




 @Override
 public String toString() {
  return "User{" +
          "email='" + email + '\'' +
          ", password='" + password + '\'' +
          ", username='" + username + '\'' +
          ", id=" + id +
          ", phone='" + phone + '\'' +
          ", created=" + created +
          ", update=" + update +
          '}';
 }
}
