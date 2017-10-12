package gusev.max.azbuka_test.presenter;

import java.util.List;

import gusev.max.azbuka_test.AzbukaApp;
import gusev.max.azbuka_test.model.Model;
import gusev.max.azbuka_test.model.ModelImpl;
import gusev.max.azbuka_test.model.data.Cart;
import gusev.max.azbuka_test.view.cart.CartView;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by v on 11/10/2017.
 */

public class CartPresenterImpl implements CartPresenter {

    private CartView view;
    private Model model = new ModelImpl();

    // Будет метод серва - будет использование
    private Subscription subscription = Subscriptions.empty();

    public CartPresenterImpl(CartView view){
        this.view = view;
    }

    @Override
    public void checkoutRequest() {
        //тук-тук на сервак
    }

    @Override
    public void getCart() {
        List<Cart.CartItem> cartItems = AzbukaApp.cart.getItemList();
        view.showCart(cartItems);
    }

    @Override
    public void onFabCLicked() {
        //отправляем корзину на серв
    }

    @Override
    public void onStop() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
