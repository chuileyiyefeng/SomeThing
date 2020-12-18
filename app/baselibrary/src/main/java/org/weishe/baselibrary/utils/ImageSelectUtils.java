package org.weishe.baselibrary.utils;

import android.content.Context;
import android.content.Intent;

import org.weishe.baselibrary.EmptyActivity;
import org.weishe.baselibrary.bean.SelectPhotoBean;
import org.weishe.baselibrary.listener.SelectResultListener;
import org.weishe.baselibrary.listener.SelectResultListenerParent;

public class ImageSelectUtils {
    private static ImageSelectUtils selectUtils;
    private static SelectPhotoBean photoBean;

    private ImageSelectUtils() {
    }

    public static ImageSelectUtils getInstance() {
        if (selectUtils == null) {
            synchronized (ImageSelectUtils.class) {
                if (selectUtils == null) {
                    photoBean = new SelectPhotoBean();
                    selectUtils = new ImageSelectUtils();
                }
            }
        }
        return selectUtils;
    }

    public ImageSelectUtils setMaxPhoto(int maxPhoto) {
        photoBean.setMaxPhoto(maxPhoto);
        return selectUtils;
    }

    public void start(Context activity) {
        Intent intent = new Intent(activity, EmptyActivity.class);
        intent.putExtra("photoBean", photoBean);
        activity.startActivity(intent);
    }




    public ImageSelectUtils setSelectResult(SelectResultListener listener) {
        SelectResultListenerParent.getInstance().setListener(listener);
        return selectUtils;
    }

}
