package com.dome.jetpack.ui.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.MenuSelectBinding;
import com.dome.jetpack.ui.base.BaseFragment;

import java.util.ArrayList;

public class MenuSelectFragment extends BaseFragment<MenuSelectBinding> {

    private final ArrayList<ImageButtonAdapter.ImageButton> buttons = new ArrayList<>();
    @Override
    protected MenuSelectBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return MenuSelectBinding.inflate(inflater,container,false);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void initView() {
        if (buttons.size() == 0) {
            buttons.add(new ImageButtonAdapter.ImageButton(R.drawable.icon_car_manager, "功能模块1", Navigation.createNavigateOnClickListener(R.id.to_nav_menu_1,null)));
            buttons.add(new ImageButtonAdapter.ImageButton(R.drawable.icon_car_manager, "功能模块2", Navigation.createNavigateOnClickListener(R.id.to_nav_menu_2,null)));
        }
        ImageButtonAdapter adapter = new ImageButtonAdapter();
        adapter.submitList(buttons);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.list.setLayoutManager(layoutManager);
        binding.list.setHasFixedSize(true);
        binding.list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onBackPress() {
        exit();
    }
}
