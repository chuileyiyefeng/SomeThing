package com.example.something.net_work.base;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.something.R;
import com.example.something.utils.StatusBarUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initPresenter();
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusTextDark(this, true);
    }

    protected abstract int bindLayout();


    protected abstract void initView();

    protected void initPresenter() {
    }

    /**
     * 启动Fragment
     *
     * @param id       id
     * @param fragment 碎片
     */
    protected void startFragment(int id, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    private Resources resources;

    @Override
    public Resources getResources() {
        if (resources == null) {
            resources = new Res(super.getResources());
        }
        return resources;
    }

    public static class Res extends Resources {

        public Res(Resources original) {
            super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
        }

        @Override
        public int getColor(int id) throws NotFoundException {
            return getColor(id, null);
        }

        @Override
        public int getColor(int id, Theme theme) throws NotFoundException {
            switch (getResourceEntryName(id)) {
                case "colorLine":
                    return 0xFF2b4e99;
                case "title_color":
                    return 0xFFD9D8DB;
                default:
                    return super.getColor(id, theme);
            }
        }
    }
}
