package gusev.max.azbuka_test.view.home;

import java.util.List;

import gusev.max.azbuka_test.model.data.Product;

/**
 * Created by v on 09/10/2017.
 */

public interface MainView {

    void showProdList(List<Product> productList);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void clearItems();
}