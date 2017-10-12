package gusev.max.azbuka_test.presenter;

import android.content.Context;

import gusev.max.azbuka_test.AzbukaApp;
import gusev.max.azbuka_test.model.data.Product;
import gusev.max.azbuka_test.view.detail.DetailView;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by v on 12/10/2017.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private Context context;
    private DetailView view;

    private Subscription subscription = Subscriptions.empty();

    public DetailPresenterImpl(DetailView view){
        this.view = view;
    }

    @Override
    public void addProductToCart(Product product) {
        view.onShowDialog("Ждёмс...");
        if(AzbukaApp.cart.addProductToCart(product)){
            view.onHideDialog();
            view.onShowToast("Добавили");
        } else {
            view.onHideDialog();
            view.onShowToast("Что-то пошло не так...");
        }

    }

    @Override
    public void onStop() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
