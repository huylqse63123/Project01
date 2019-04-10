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
public class Item implements Serializable {

   private ShoesDTO shoes;
   private String sizeID;
   private int quantity;

   public Item() {
   }

   public Item(ShoesDTO shoes, int quantity) {
      this.shoes = shoes;
      this.quantity = quantity;
   }

   public Item(ShoesDTO shoes, int quantity, String sizeID) {
      this.shoes = shoes;
      this.quantity = quantity;
      this.sizeID = sizeID;
   }

   public ShoesDTO getShoes() {
      return shoes;
   }

   public void setShoes(ShoesDTO shoes) {
      this.shoes = shoes;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public float totalLine() {
      return shoes.getPrice() * quantity;
   }

   public Item intQuantity(Item i) {
      Item item = new Item(shoes, quantity);
      item.quantity += i.getQuantity();
      return item;
   }

   public String getSizeID() {
      return sizeID;
   }

   public void setSizeID(String sizeID) {
      this.sizeID = sizeID;
   }
}
