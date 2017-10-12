package gusev.max.azbuka_test.model.api;

import java.util.List;

import gusev.max.azbuka_test.model.data.Product;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by v on 08/10/2017.
 */

public interface ApiInterface {

    @GET("products")
    Observable<List<Product>> getProducts();

}
