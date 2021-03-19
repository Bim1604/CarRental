/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable{
    public void addItemtoCart(String title) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }       
         int quantity =1;
        //2. Check items not existed
        if (this.items.containsKey(title)){
            quantity = this.items.get(title) + 1;
        }
        
        this.items.put(title, quantity);
    }

    public void removeItemFromCart(String title) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(title)) {
            this.items.remove(title);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public void removeCart(){
        this.items = null;
    }
}
