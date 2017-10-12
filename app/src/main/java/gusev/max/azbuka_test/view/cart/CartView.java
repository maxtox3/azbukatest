package gusev.max.azbuka_test.view.cart;

import java.util.List;

import gusev.max.azbuka_test.model.data.Cart;

/**
 * Created by v on 09/10/2017.
 */

public interface CartView {

    void showCart(List<Cart.CartItem> cartItems);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void clearCart();

}
