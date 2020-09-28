package com.example.something;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.something.kotlin_test.KotlinActivity;
import com.example.something.mvp.MvpActivity;
import com.example.something.net_work.TestNetWorkActivity;
import com.example.something.utils.StatusBarUtil;

import org.jetbrains.annotations.NotNull;
import org.weishe.baselibrary.listener.SelectResultListener;
import org.weishe.baselibrary.utils.ImageSelectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusTextDark(this, true);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_net, R.id.tv_mvp, R.id.tv_rx_java, R.id.tv_kotlin, R.id.tv_time_picker, R.id.tv_photo})
    public void onClick(@NotNull View v) {
        switch (v.getId()) {
            case R.id.tv_net:
                startActivity(new Intent(this, TestNetWorkActivity.class));
                break;
            case R.id.tv_kotlin:
                startActivity(new Intent(this, KotlinActivity.class));
                break;
            case R.id.tv_mvp:
                startActivity(new Intent(this, MvpActivity.class));
                break;
            case R.id.tv_rx_java:
                startActivity(new Intent(this, TestRxJavaActivity.class));
                break;
            case R.id.tv_photo:
                ImageSelectUtils.getInstance().setMaxPhoto(1).setSelectResult(new SelectResultListener() {
                    @Override
                    public void selectResult(ArrayList<String> strings) {
                        Toast.makeText(MainActivity.this, strings.get(0), Toast.LENGTH_SHORT).show();
                    }
                }).start(this);
                break;
            case R.id.tv_time_picker:
                TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Toast.makeText(MainActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                    }
                })
                        .setType(new boolean[]{true, true, false, false, false, false})//分别对应年月日时分秒，默认全部显示
                        .setLabel("", "", "", "", "", "")
                        .isDialog(true)
                        .build();

                Dialog mDialog = pvTime.getDialog();
                if (mDialog != null) {

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.BOTTOM);

                    params.leftMargin = 0;
                    params.rightMargin = 0;
                    pvTime.getDialogContainerLayout().setLayoutParams(params);

                    Window dialogWindow = mDialog.getWindow();
                    if (dialogWindow != null) {
                        dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                        dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                    }
                }
                pvTime.show();
                break;
        }
    }

    protected String getTime(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");

        return format.format(date);
    }

}
