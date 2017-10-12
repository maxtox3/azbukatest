package gusev.max.azbuka_test.presenter;

/**
 * Created by v on 09/10/2017.
 */

public interface Presenter {

    void getProducts();

    void getProductsFromDB();

    void onStop();

    void getSortedByPriceUpProducts();

    void getSortedByPriceDownProducts();

}