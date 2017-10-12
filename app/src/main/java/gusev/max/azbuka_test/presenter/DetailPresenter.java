package gusev.max.azbuka_test.presenter;

import gusev.max.azbuka_test.model.data.Product;

/**
 * Created by v on 12/10/2017.
 */

public interface DetailPresenter {

    void addProductToCart(Product product);

    void onStop();
}
