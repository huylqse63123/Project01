/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author cwalk
 */
public class AccountDTO implements Serializable{
   private String username;
   private String lastName;
   private String middleName;
   private String firstName;
   private String address;
   private String phone;
   
   public AccountDTO() {
   }

   public AccountDTO(String username, String lastName, String middleName, String firstName, String address, String phone) {
      this.username = username;
      this.lastName = lastName;
      this.middleName = middleName;
      this.firstName = firstName;
      this.address = address;
      this.phone = phone;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }


   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getFullname() {
      return firstName + middleName + lastName;
   }
}
