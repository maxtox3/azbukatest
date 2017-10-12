package gusev.max.azbuka_test;

import android.app.Application;

import gusev.max.azbuka_test.model.data.Cart;

/**
 * Created by v on 11/10/2017.
 */

public class AzbukaApp extends Application {

    //Это супер тупо, планировал делать все через кэш - бд, но мозг отсох
    public static Cart cart;

    @Override
    public void onCreate() {
        super.onCreate();
        cart = new Cart();
    }
}
