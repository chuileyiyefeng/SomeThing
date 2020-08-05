package org.weishe.baselibrary.bean;

import org.weishe.baselibrary.utils.ImageSelectUtils;

import java.io.Serializable;

public class SelectPhotoBean implements Serializable {
    // 最大图片选择数
    private int maxPhoto;

    public int getMaxPhoto() {
        return maxPhoto;
    }

    public void setMaxPhoto(int maxPhoto) {
        this.maxPhoto = maxPhoto;
    }
}
