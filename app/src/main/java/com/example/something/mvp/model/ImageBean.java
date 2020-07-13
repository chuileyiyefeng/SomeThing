package com.example.something.mvp.model;

import android.graphics.Bitmap;

import com.example.something.mvp.base.BaseEntity;

public class ImageBean extends BaseEntity {
    private String path;
    private Bitmap bitmap;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
