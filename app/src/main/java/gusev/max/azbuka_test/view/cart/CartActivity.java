package gusev.max.azbuka_test.view.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.model.data.Cart;
import gusev.max.azbuka_test.presenter.CartPresenterImpl;
import gusev.max.azbuka_test.view.BaseActivity;

/**
 * Created by v on 11/10/2017.
 */

public class CartActivity extends BaseActivity implements CartView, View.OnClickListener{

    @Bind(R.id.cart_items_list)
    protected RecyclerView cartItemsList;

    @Bind(R.id.fabButton)
    protected FloatingActionButton floatingActionButton;

    private CartAdapter adapter;
    private CartPresenterImpl presenter;

    //BaseActivity methods

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initList();
        showBackArrow();
        setTitle("Корзина");
        presenter = new CartPresenterImpl(this);
        presenter.getCart();
    }

    private void initList() {
        cartItemsList.setHasFixedSize(true);
        cartItemsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CartAdapter(getLayoutInflater());
        cartItemsList.setAdapter(adapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cart;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //CartView methods

    @Override
    public void showCart(List<Cart.CartItem> cartItems) {
        adapter.addCartItems(cartItems);
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
        Toast.makeText(CartActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearCart() {
        adapter.clearCartItems();
    }

    @Override
    public void onClick(View view) {
        presenter.onFabCLicked();
    }
}
