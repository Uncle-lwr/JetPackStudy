package com.dome.jetpack.ui.fragment.menu.menu1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.Menu1BranchBinding;
import com.dome.jetpack.ui.base.BaseFragment;
import com.dome.jetpack.ui.fragment.menu.menu1.viewmodel.Menu1ViewModel;

public class Menu1BranchFragment extends BaseFragment<Menu1BranchBinding> {
    private Menu1ViewModel viewModel;
    @Override
    protected Menu1BranchBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return Menu1BranchBinding.inflate(inflater,container,false);
    }

    @Override
    protected void bindViewModel() {
        viewModel = new ViewModelProvider(getController().getViewModelStoreOwner(R.id.nav_menu_1)).get(Menu1ViewModel.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        binding.butToHome.setOnClickListener(v -> navigate(R.id.popup_to_home));
    }
}
