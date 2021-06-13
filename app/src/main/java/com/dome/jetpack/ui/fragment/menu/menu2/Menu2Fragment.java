package com.dome.jetpack.ui.fragment.menu.menu2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.Menu2Binding;
import com.dome.jetpack.ui.base.BaseFragment;
import com.dome.jetpack.ui.fragment.menu.menu2.viewmodel.Menu2ViewModel;

public class Menu2Fragment extends BaseFragment<Menu2Binding> {
    private Menu2ViewModel viewModel;
    @Override
    protected Menu2Binding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return Menu2Binding.inflate(inflater,container,false);
    }

    @Override
    protected void bindViewModel() {
        viewModel = new ViewModelProvider(getController().getViewModelStoreOwner(R.id.nav_menu_2)).get(Menu2ViewModel.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        binding.butToFragment.setOnClickListener(v -> navigate(R.id.to_menu2BranchFragment));
    }
}
