package com.dome.jetpack.ui.fragment.menu;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.ItemImageButtonBinding;
import com.dome.jetpack.ui.base.BaseAdapter;

import org.jetbrains.annotations.NotNull;

/***
 * 菜单适配器
 */
public class ImageButtonAdapter extends BaseAdapter<ImageButtonAdapter.ImageButton, ItemImageButtonBinding> {

    public ImageButtonAdapter() {
        super(null);
    }


    @Override
    protected ItemImageButtonBinding createBinding(LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(inflater, R.layout.item_home_but_layout, parent, false);
    }

    @Override
    protected void bindObject(@NonNull ItemImageButtonBinding binding, @NonNull ImageButton item, int position) {
        binding.setButton(item);
    }

    @Override
    protected void handleItemNull(ItemImageButtonBinding binding, int position) {

    }
    //BindingAdapter自定义样式
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @BindingAdapter("drawableImg")
    public static void drawableImg(@NotNull ImageView imageView, @DrawableRes int resourceId) {
        imageView.setImageDrawable(imageView.getContext().getDrawable(resourceId));
    }

    public static class ImageButton {

        @DrawableRes
        int icon;
        String title;
        View.OnClickListener listener;

        public ImageButton(int icon, String title, View.OnClickListener listener) {
            this.icon = icon;
            this.title = title;
            this.listener = listener;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public View.OnClickListener getListener() {
            return listener;
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;
        }
    }
}
