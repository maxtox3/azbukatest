package gusev.max.azbuka_test.view.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import gusev.max.azbuka_test.R;
import gusev.max.azbuka_test.helpers.ImageHandler;
import gusev.max.azbuka_test.model.data.Product;

/**
 * Created by v on 09/10/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private final Context context;
    private int layoutResourceId;
    private final List<Product> productList = new ArrayList<>();

    public GridViewAdapter(Context context, int layoutResourceId) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    public void addProducts(List<Product> list) {
        productList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearProducts() {
        productList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Product product = productList.get(i);
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, viewGroup, false);

            holder = new ViewHolder(
                    view.findViewById(R.id.prod_preview_name),
                    view.findViewById(R.id.prod_preview_price),
                    view.findViewById(R.id.prod_preview_image));

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.nameTextview.setText(product.getName());
        holder.priceTextView.setText("Цена: " + product.getPrice() + "р.");
        Glide.with(context).load(product.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(holder.imageView));

        return view;
    }

    private class ViewHolder {
        private final TextView nameTextview;
        private final TextView priceTextView;
        private final ImageView imageView;

        ViewHolder(TextView nameTextView, TextView priceTextView, ImageView imageView) {
            this.nameTextview = nameTextView;
            this.priceTextView = priceTextView;
            this.imageView = imageView;
        }
    }


}
