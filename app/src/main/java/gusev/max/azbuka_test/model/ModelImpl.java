package gusev.max.azbuka_test.model;

import java.util.List;

import gusev.max.azbuka_test.model.api.ApiInterface;
import gusev.max.azbuka_test.model.api.ApiModule;
import gusev.max.azbuka_test.model.data.Product;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by v on 09/10/2017.
 */

public class ModelImpl implements Model {

    private ApiInterface apiInterface = ApiModule.getApiInteface();

    @Override
    public Observable<List<Product>> getProducts() {
        return apiInterface
                .getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}