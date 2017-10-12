package gusev.max.azbuka_test.view.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.helpers.ImageHandler;
import gusev.max.azbuka_test.model.data.Product;
import gusev.max.azbuka_test.presenter.DetailPresenterImpl;
import gusev.max.azbuka_test.view.BaseActivity;

/**
 * Created by v on 11/10/2017.
 */

public class DetailActivity extends BaseActivity implements DetailView, View.OnClickListener{

    public static final String PRODUCT = "product";

    @Bind(R.id.product_name) protected TextView productNameTextView;
    @Bind(R.id.product_price) protected TextView productPriceTextView;
    @Bind(R.id.product_description) protected TextView productDescriptionTextView;
    @Bind(R.id.product_image) protected ImageView productImageView;
    @Bind(R.id.add_to_cart_button) protected Button addToCartButton;

    private Product product;
    private DetailPresenterImpl presenter;


    //BaseActivity methods

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addToCartButton.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            productImageView.setTransitionName  ("productImageAnimation");
        }

        presenter = new DetailPresenterImpl(this);

        showBackArrow();

        product = (Product) intent.getSerializableExtra(PRODUCT);
        setTitle(product.getName());

        productNameTextView.setText(product.getName());
        productPriceTextView.setText(product.getPrice());
        productDescriptionTextView.setText(product.getDescription());

        Glide.with(this).load(product.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(productImageView));

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        presenter.addProductToCart(product);
    }

    //DetailView methods

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
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
