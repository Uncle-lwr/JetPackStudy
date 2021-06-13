package com.dome.jetpack.ui.fragment.branch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

/**
 * 管理生命周期（数据逻辑处理）
 */
public class BranchViewModel extends AndroidViewModel {
    private final MutableLiveData<Integer> sum = new MutableLiveData<>(0);

    public MutableLiveData<Integer> getSum() {
        return sum;
    }

    public BranchViewModel(@NonNull Application application) {
        super(application);
    }

    public void addSum(){
        int value = Objects.requireNonNull(getSum().getValue());
        getSum().setValue(value+1);
    }
    public void loseSum(){
        int value = Objects.requireNonNull(getSum().getValue());
        getSum().setValue(value==0?0:value-1);
    }
}
