/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author cwalk
 */
public class Validation implements Serializable{
   private String usernameExist;
   private String usernameLengthErr;
   private String passwordErr;
   private String confirmPassword;
   private String lNameErr;
   private String mNameErr;
   private String fNameErr;
   private String address;
   private String phone;

   public Validation() {
   }

   public Validation(String usernameExist, String usernameLengthErr, String passwordErr, String confirmPassword, 
           String lNameErr, String mNameErr, String fNameErr, String address, String phone) {
      this.usernameExist = usernameExist;
      this.usernameLengthErr = usernameLengthErr;
      this.passwordErr = passwordErr;
      this.confirmPassword = confirmPassword;
      this.lNameErr = lNameErr;
      this.mNameErr = mNameErr;
      this.fNameErr = fNameErr;
      this.address = address;
      this.phone = phone;
   }

   public String getUsernameExist() {
      return usernameExist;
   }

   public void setUsernameExist(String usernameExist) {
      this.usernameExist = usernameExist;
   }

   public String getUsernameLengthErr() {
      return usernameLengthErr;
   }

   public void setUsernameLengthErr(String usernameLengthErr) {
      this.usernameLengthErr = usernameLengthErr;
   }

   public String getPasswordErr() {
      return passwordErr;
   }

   public void setPasswordErr(String passwordErr) {
      this.passwordErr = passwordErr;
   }

   public String getConfirmPassword() {
      return confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
      this.confirmPassword = confirmPassword;
   }

   public String getlNameErr() {
      return lNameErr;
   }

   public void setlNameErr(String lNameErr) {
      this.lNameErr = lNameErr;
   }

   public String getmNameErr() {
      return mNameErr;
   }

   public void setmNameErr(String mNameErr) {
      this.mNameErr = mNameErr;
   }

   public String getfNameErr() {
      return fNameErr;
   }

   public void setfNameErr(String fNameErr) {
      this.fNameErr = fNameErr;
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
   
}
