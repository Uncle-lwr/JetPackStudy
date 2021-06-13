package com.dome.jetpack.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.LoginFragmentBinding;
import com.dome.jetpack.ui.base.BaseFragment;

public class LoginFragment extends BaseFragment<LoginFragmentBinding> {
    @Override
    protected LoginFragmentBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LoginFragmentBinding.inflate(inflater,container,false);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        binding.butLogin.setOnClickListener(v -> navigate(R.id.action_to_nav_home));
    }
}
