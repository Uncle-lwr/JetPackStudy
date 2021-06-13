package com.dome.jetpack.ui.fragment.branch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.BranchTestBinding;
import com.dome.jetpack.ui.base.BaseFragment;

public class BranchTestFragment extends BaseFragment<BranchTestBinding> {

    //这个可以管理同一个navigation的数据处理
    private BranchViewModel viewModel;
    @Override
    protected BranchTestBinding setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return BranchTestBinding.inflate(inflater,container,false);
    }

    @Override
    protected void bindViewModel() {
        //绑定viewModel管理navigation的生命周期
        viewModel = new ViewModelProvider(getController().getViewModelStoreOwner(R.id.nav_home)).get(BranchViewModel.class);
        //设置viewModel与布局绑定
        binding.setViewModel(viewModel);
        //设置当前fragment生命周期为观察者模式）
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {
        binding.butAdd.setOnClickListener(v -> viewModel.addSum());
        binding.butLose.setOnClickListener(v -> viewModel.loseSum());
    }
}
