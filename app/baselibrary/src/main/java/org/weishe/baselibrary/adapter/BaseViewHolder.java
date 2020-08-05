package org.weishe.baselibrary.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Tmp on 2018/12/18.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public View itemView;
    private SparseArray<View> mViews;

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        mViews = new SparseArray<>();
    }

    public void setText(int id, String text) {
        TextView tv = itemView.findViewById(id);
        tv.setText(text);
    }
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
