package gusev.max.azbuka_test.view.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.model.data.Product;
import gusev.max.azbuka_test.presenter.ProdListPresenterImpl;
import gusev.max.azbuka_test.utilities.NetworkUtil;
import gusev.max.azbuka_test.view.BaseActivity;
import gusev.max.azbuka_test.view.cart.CartActivity;
import gusev.max.azbuka_test.view.detail.DetailActivity;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.grid_view)
    GridView gridView;

//    @Bind(R.id.action_show_cart)
//    MenuItem cartButton;

    private GridViewAdapter adapter;
    private ProdListPresenterImpl presenter;

    //BaseActivity methods

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        adapter = new GridViewAdapter(this, R.layout.grid_item_layout);
        gridView.setAdapter(adapter);
        presenter = new ProdListPresenterImpl(this, getApplicationContext());

        loadProducts();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private void loadProducts() {
        if (NetworkUtil.isNetAvailable(this)) {
            presenter.getProducts();
        } else {
            presenter.getProductsFromDB();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_cart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_price_up:
                presenter.getSortedByPriceUpProducts();
                return true;
            case R.id.action_price_down:
                presenter.getSortedByPriceDownProducts();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //ProdListView methods

    @Override
    public void showProdList(List<Product> productList) {
        adapter.addProducts(productList);
        gridView.setOnItemClickListener((parent, v, position, id) -> {
            Product product = (Product) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.PRODUCT, product);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "productImageAnimation");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearItems() {
        adapter.clearProducts();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }
}
