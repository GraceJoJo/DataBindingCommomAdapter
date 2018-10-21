package com.example.jojo.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by JoJo on 2018/8/29.
 * <p>
 * wechat：18510829974
 * description：Databinding中的ImageView与Glide结合使用,自定义ImageViewBindingAdapter
 */
public class ViewBindingAdapter {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH);
        Glide.with(MyApplication.context)
                .load(url)
                .apply(requestOptions)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

}
