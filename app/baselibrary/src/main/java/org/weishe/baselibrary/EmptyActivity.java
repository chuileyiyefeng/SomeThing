package org.weishe.baselibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.weishe.baselibrary.bean.SelectPhotoBean;
import org.weishe.baselibrary.listener.SelectResultListenerParent;

import java.util.ArrayList;

public class EmptyActivity extends AppCompatActivity {
    int code = 100;
    SelectPhotoBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = (SelectPhotoBean) getIntent().getSerializableExtra("photoBean");
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra("photoBean", bean);
        startActivityForResult(intent, code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == code) {
            if (data != null) {
                ArrayList<String> pathList = data.getStringArrayListExtra("data");
                if (pathList != null && pathList.size() > 0) {
                    SelectResultListenerParent.getInstance().getListener().selectResult(pathList);
                }
            }
            finish();
        }
    }

}
