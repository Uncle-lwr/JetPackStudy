package com.dome.jetpack.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<V extends ViewBinding> extends Fragment {

    //ViewDataBinding  属于DataBinding
    //ViewBinding 属于ViewBinding，ViewBinding不能进行双向绑定，字段绑定等操作，所以一律不用ViewBinding。

    protected V binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = setBinding(inflater, container, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViewModel();
        initView();
        initListener();

        //绑定系统返回键==NavigationUI返回键
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onBackPress();
            }
        });
    }

    /**
     * set Binding
     */
    protected abstract V setBinding(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 绑定ViewModel
     */
    protected abstract void bindViewModel();
    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 设置监听初始化
     */
    protected abstract void initListener();


    protected NavController getController() {
        return NavHostFragment.findNavController(this);
    }

    /**
     * 启动新的nav节点
     */
    protected void navigate(int id) {
        getController().navigate(id);
    }

    protected void navigate(int id, Bundle bundle) {
        getController().navigate(id, bundle);
    }

    /**
     * Navigation管理，Fragment回退
     */
    protected void navigateUp() {
        NavHostFragment.findNavController(this).navigateUp();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //页面使用结束释放资源
        binding = null;
    }


    protected void showToast(String text, int duration) {
        Toast.makeText(requireContext(), text, duration).show();
    }

    protected void showSnackBar(View view, String msg, int duration) {
        Snackbar.make(view, msg, duration).show();
    }

    protected void showDialog(@NotNull DialogFragment dialog) {
        dialog.show(getParentFragmentManager(), dialog.toString());
    }

    protected void showConfirmDialog(String title, String message, DialogInterface.OnClickListener listener) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("是", listener)
                .setNegativeButton("否", (dialog, which) -> {
                }).show();
    }

    /**
     * 显示提示Dialog
     */
    protected void showConfirmDialog(String title, String message) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("确认", (dialog, which) -> {
                }).show();
    }


    /**
     * 弹出输入框
     *
     * @param editText
     */
    protected void showSoftKeyboard(EditText editText) {
        try {
            if (editText != null) {
                editText.requestFocus();
                InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(editText, 0);
            }
        } catch (Exception ex) {
            Log.i("ERROR", ex.getMessage());
        }
    }

    /**
     * 隐藏输入框
     */
    protected void hideSoftKeyboard() {
        try {
            View view = requireActivity().getCurrentFocus();
            if (view != null) {
                IBinder binder = view.getWindowToken();
                InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception ex) {
            Log.i("ERROR", ex.getMessage());
        }
    }

    /**
     * 显示输入法键盘
     */
    public void showKeyBoard(View view) {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 收起输入法键盘
     */
    public void cancelKeyBoard(View view) {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * 退出
     */
    protected void exit() {
        requireActivity().finish();
    }

    /**
     * 回退
     */
    protected void onBackPress() {
        Log.i("Back", "onBackPress");
        navigateUp();
    }

}

