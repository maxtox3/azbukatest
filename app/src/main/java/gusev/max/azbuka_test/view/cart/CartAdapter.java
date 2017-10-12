package gusev.max.azbuka_test.view.cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.helpers.ImageHandler;
import gusev.max.azbuka_test.model.data.Cart;

/**
 * Created by v on 11/10/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    private LayoutInflater layoutInflater;
    private List<Cart.CartItem> cartItemsList = new ArrayList<>();

    public CartAdapter(LayoutInflater inflater) {
        this.layoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(cartItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }

    void addCartItems(List<Cart.CartItem> items) {
        cartItemsList.addAll(items);
        notifyDataSetChanged();
    }

    void clearCartItems() {
        cartItemsList.clear();
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.cart_image_view)
        ImageView image;
        @Bind(R.id.cart_product_name)
        TextView name;
        @Bind(R.id.cart_product_quantity)
        TextView quantity;
        @Bind(R.id.cart_product_amount)
        TextView amount;

        private Context context;
        private Cart.CartItem item;

        Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        void bind(Cart.CartItem cartItem) {
            item = cartItem;
            name.setText(item.getItemName());
            quantity.setText("Количество: " + item.getItemQty());
            amount.setText("Цена: " + item.getAmount() + " р.");

            Glide.with(context).load(item.getItemImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(new ImageHandler(image));
        }
    }
}
