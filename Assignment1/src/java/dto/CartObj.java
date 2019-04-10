/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author cwalk
 */
public class CartObj implements Serializable{
   private String code;
   private Map<String, Item> cart = new HashMap<>();

   public CartObj() {
      this.code = genCode();
   }

   private String genCode() {
      return String.format("LZD%05d", System.currentTimeMillis() % 100000);
   }
   
   public void addProduct(ShoesDTO shoes, String size, String sizeID) {
      Item item = cart.get(shoes.getId() + size);
      if (item != null) {
         item.setQuantity(item.getQuantity() + 1);
      } else {
         cart.put(shoes.getId() + size, new Item(shoes, 1, sizeID));
      }
   }

   public void remove(String title) {
      if (this.cart == null) {
         return;
      }
      if (this.cart.containsKey(title)) {
         this.cart.remove(title);
      }
   }
   
   public Collection<Item> getABC() {
      Collection<Item> test = cart.values().stream()
              .collect(Collectors.toMap(e -> e.getShoes().getId(), e -> e, Item::intQuantity))
              .values();
      return test;
   }
   
   public String getCode() {
      return code;
   }

   public Map<String, Item> getCart() {
      return cart;
   }
   
   public float total() {
      return (float) cart.values().stream()
              .mapToDouble(s -> s.totalLine())
              .sum();
   }
}
