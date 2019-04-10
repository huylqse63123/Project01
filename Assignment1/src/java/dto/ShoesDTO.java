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
public class ShoesDTO implements Serializable{
   private String id;
   private String description;
   private float price;
   private int quantity;
   private int size;
   
   public ShoesDTO() {
   }

   public ShoesDTO(String id, String description, int quantity) {
      this.id = id;
      this.description = description;
      this.quantity = quantity;
   }

   public ShoesDTO(String id, String description, float price, int quantity, int size) {
      this.id = id;
      this.description = description;
      this.price = price;
      this.quantity = quantity;
      this.size = size;
   }

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

}
