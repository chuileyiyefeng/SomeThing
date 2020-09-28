package org.weishe.baselibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.weishe.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends BaseAdapter<String> {

    private int realWidth, realHeight;
    private ArrayList<String> selectPath;

    public PhotoAdapter(Context context, int width) {
        super(context);
        selectPath = new ArrayList<>();
        realWidth = width;
        realHeight = realWidth;
    }

    @Override
    protected int bindLayout() {
        return R.layout.adapter_photo;
    }

    @Override
    protected void bindHolder(@NonNull BaseViewHolder holder, int i) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.width = realWidth;
        params.height = realHeight;
        holder.itemView.setLayoutParams(params);
        final String path = getItem(i);
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        final ImageView ivSelect = holder.getView(R.id.iv_select);
        ivSelect.setVisibility(selectPath.contains(path) ? View.VISIBLE : View.GONE);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPath.contains(path)) {
                    selectPath.remove(path);
                    ivSelect.setVisibility(View.GONE);
                } else {
                    if (selectPath.size() >= maxPhoto) {
                        Toast.makeText(context, "最多选择" + maxPhoto + "张图片!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selectPath.add(path);
                    ivSelect.setVisibility(View.VISIBLE);
                }
            }
        });
        Glide.with(context)
                .load(path)
//                .centerCrop()
                .placeholder(R.mipmap.pic_loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ivPhoto);

    }

    public ArrayList<String> getData() {
        return selectPath;
    }

    private int maxPhoto;

    public void setMaxSelect(int maxPhoto) {
        this.maxPhoto = maxPhoto;
    }
}