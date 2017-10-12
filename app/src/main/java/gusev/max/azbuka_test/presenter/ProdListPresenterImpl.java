package gusev.max.azbuka_test.presenter;

import android.content.Context;

import java.util.List;

import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.model.Model;
import gusev.max.azbuka_test.model.ModelImpl;
import gusev.max.azbuka_test.model.Storage;
import gusev.max.azbuka_test.model.data.Product;
import gusev.max.azbuka_test.view.home.MainView;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by v on 09/10/2017.
 */

public class ProdListPresenterImpl implements Presenter {

    private Model model = new ModelImpl();
    private MainView view;
    private Context context;
    private Storage storage;

    private Subscription subscription = Subscriptions.empty();

    public ProdListPresenterImpl(MainView view, Context context) {
        this.view = view;
        this.context = context;
        storage = new Storage(context);
    }

    //get methods from api

    @Override
    public void getProducts() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        view.onShowDialog("Ждёмс...");

        subscription = model.getProducts()
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onCompleted() {
                        view.onHideDialog();
                        view.onShowToast("Загрузили");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onHideDialog();
                        view.onShowToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Product> productList) {
                        if (productList != null && !productList.isEmpty()) {
                            for (Product product: productList) {
                                storage.addProduct(product);
                            }
                            view.clearItems();
                            view.showProdList(productList);
                        } else {
                            view.onShowDialog(context.getString(R.string.empty_products_list));
                        }
                    }
                });
    }

    //get methods from db

    @Override
    public void getProductsFromDB() {
        List<Product> productList = storage.getSavedProducts();
        view.clearItems();
        view.showProdList(productList);
    }

    @Override
    public void getSortedByPriceUpProducts() {
        List<Product> productList = storage.getProductsByPrice();
        view.clearItems();
        view.showProdList(productList);
    }

    @Override
    public void getSortedByPriceDownProducts() {
        List<Product> productList = storage.getProductsByPriceDESC();
        view.clearItems();
        view.showProdList(productList);
    }

    //

    @Override
    public void onStop() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }


}
