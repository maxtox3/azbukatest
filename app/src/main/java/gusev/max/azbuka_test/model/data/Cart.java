package gusev.max.azbuka_test.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 11/10/2017.
 */

public class Cart {

    private List<CartItem> itemList;

    public Cart() {
        this.itemList = new ArrayList<>();
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    private CartItem getItemViaId(int id) {
        CartItem item;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemId() == id) {
                item = itemList.get(i);
                itemList.remove(i);
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //Писал ночью, когда мозг уже отсох, поэтому такая какафония, сорян:(

    public boolean addProductToCart(Product product) {
        List<CartItem> itemList = this.getItemList();
        CartItem item;
        if (!itemList.isEmpty()) {
            if ((item = getItemViaId(product.getId())) != null) {
                item.itemQty++;
                item.amount = item.itemQty * Double.parseDouble(product.getPrice());
                itemList.add(item);
            } else {
                itemList.add(new CartItem(product.getId(), 1, Double.parseDouble(product.getPrice()), product.getName(), product.getImgUrl()));
            }
        } else {
            itemList.add(new CartItem(product.getId(), 1, Double.parseDouble(product.getPrice()), product.getName(), product.getImgUrl()));
        }
        return true;
    }


    public class CartItem {

        private int itemId;
        private int itemQty;
        private double amount;
        private String itemName;
        private String itemImageUrl;

        CartItem(int itemId, int itemQty, double amount, String name, String itemImageUrl) {
            this.itemId = itemId;
            this.itemQty = itemQty;
            this.amount = amount;
            this.itemName = name;
            this.itemImageUrl = itemImageUrl;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemQty() {
            return String.valueOf(itemQty);
        }

        public void setItemQty(int itemQty) {
            this.itemQty = itemQty;
        }

        public String getAmount() {
            return String.valueOf(amount);
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemImageUrl() {
            return itemImageUrl;
        }

        public void setItemImageUrl(String itemImageUrl) {
            this.itemImageUrl = itemImageUrl;
        }
    }
}