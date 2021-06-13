package com.dome.jetpack.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


/***
 *设置布局绑定、点击事件接口
 */
public abstract class BaseAdapter<T, V extends ViewDataBinding> extends ListAdapter<T, BaseDataViewHolder<V>> {
    protected OnItemClickListener<T> onItemClickListener;

    protected BaseAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BaseDataViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        V binding = createBinding(inflater, parent, viewType);
        return new BaseDataViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDataViewHolder<V> holder, int position) {
        T t = getItem(position);
        if (t == null) {
            handleItemNull(holder.getBinding(), position);
        } else {
            bindObject(holder.getBinding(), t, position);
        }
        holder.getBinding().executePendingBindings();
    }

    /**
     * 返回Item的DataBinding
     *
     * @param inflater inflater
     * @param parent   parent
     * @param viewType viewType
     * @return dataBinding
     */
    protected abstract V createBinding(LayoutInflater inflater, @NonNull ViewGroup parent, int viewType);

    /**
     * DataBinding 设置值
     *
     * @param binding  binding
     * @param item     item
     * @param position position
     */
    protected abstract void bindObject(@NonNull V binding, @NonNull T item, int position);

    /**
     * 拓展处理item为空的情况。如placeHolder
     *
     * @param binding  binding
     * @param position position
     */
    protected abstract void handleItemNull(V binding, int position);

}
