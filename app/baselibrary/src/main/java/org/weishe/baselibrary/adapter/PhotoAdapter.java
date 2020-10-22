package org.weishe.baselibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.weishe.baselibrary.R;

import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter<String> {

    private int realWidth, realHeight;
    private ArrayList<String> selectPathList;

    public PhotoAdapter(Context context, int width) {
        super(context);
        selectPathList = new ArrayList<>();
        realWidth = width;
        realHeight = width;
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
        ivSelect.setVisibility(selectPathList.contains(path) ? View.VISIBLE : View.GONE);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPathList.contains(path)) {
                    selectPathList.remove(path);
                    ivSelect.setVisibility(View.GONE);
                } else {
                    if (selectPathList.size() >= maxPhoto) {
                        Toast.makeText(context, "最多选择" + maxPhoto + "张图片!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selectPathList.add(path);
                    ivSelect.setVisibility(View.VISIBLE);
                }
                if (selectCountListener!=null) {
                    selectCountListener.selectCount(selectPathList.size());
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
        return selectPathList;
    }

    private int maxPhoto;

    public void setMaxSelect(int maxPhoto) {
        this.maxPhoto = maxPhoto;
    }

    private SelectCountListener selectCountListener;

    public void setSelectCountListener(SelectCountListener selectCountListener) {
        this.selectCountListener = selectCountListener;
    }

    public interface SelectCountListener {
        void selectCount(int count);
    }
}