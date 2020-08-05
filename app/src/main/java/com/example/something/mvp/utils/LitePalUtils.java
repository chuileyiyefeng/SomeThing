package com.example.something.mvp.utils;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LitePalUtils<T> {

    public T getFirstData(Class<T> modelClass) {
        List<T> list = DataSupport.findAll(modelClass);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
