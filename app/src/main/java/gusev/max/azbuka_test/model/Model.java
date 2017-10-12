package gusev.max.azbuka_test.model;

import java.util.List;

import gusev.max.azbuka_test.model.data.Product;
import rx.Observable;

/**
 * Created by v on 09/10/2017.
 */

public interface Model {

    Observable<List<Product>> getProducts();

}
