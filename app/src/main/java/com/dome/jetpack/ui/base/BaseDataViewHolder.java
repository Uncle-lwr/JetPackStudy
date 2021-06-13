package com.dome.jetpack.ui.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 绑定binding为viewHolder布局Id
 */
public class BaseDataViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final T binding;

    BaseDataViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
