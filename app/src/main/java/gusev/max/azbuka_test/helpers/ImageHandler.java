package gusev.max.azbuka_test.helpers;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by v on 11/10/2017.
 */

public class ImageHandler extends SimpleTarget<GlideDrawable> {

    private ImageView imageView;

    public ImageHandler(ImageView imageView) {
        this.imageView = imageView;
    }


    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
        imageView.setImageDrawable(resource);
        GlideBitmapDrawable drawable = (GlideBitmapDrawable) imageView.getDrawable().getCurrent();
        Bitmap bitmap = drawable.getBitmap();
    }
}
