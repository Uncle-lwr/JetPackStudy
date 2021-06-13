package com.dome.jetpack.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dome.jetpack.R;
import com.dome.jetpack.databinding.MainDataBinding;

public class MainActivity extends AppCompatActivity {
    //利用DataBinding绑定界面id
    private MainDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setStatusBarFullTransparent();
        initView();
        initMenu();
    }

    /**
     * 初始化NavigationUI与Toolbar绑定（用NavigationUI记得在系统样式中设置（在styles.xml或themes.xml中修改）Theme.MaterialComponents.DayNight.NoActionBa）
     */
    private void initView() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.tbMain, navController, appBarConfiguration);
        navController.addOnDestinationChangedListener((controller, destination, arguments) ->
                binding.tbMain.setVisibility(destination.getId() == R.id.loginFragment2 ? View.GONE : View.VISIBLE));
        //设置toolbar中的返回键为系统返回键
        binding.tbMain.setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * 初始化菜单栏
     */
    protected void initMenu() {
        binding.tbMain.inflateMenu(R.menu.menu_home);
        binding.tbMain.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.setting) {
                //跳转到分支
                getController().navigate(R.id.to_branchTestFragment);
            } else if (id == R.id.logout) {
                //退出到登录界面
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    /**
     * 设置nav的跳转处理（一般写在BaseActivity中）
     */
    protected NavController getController() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        return navHostFragment.getNavController();
    }

    /**
     * 全透状态栏（一般写在BaseActivity中）
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}