package org.weishe.baselibrary.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import org.weishe.baselibrary.AlbumInfo;
import org.weishe.baselibrary.R;
import org.weishe.baselibrary.adapter.BaseAdapter;
import org.weishe.baselibrary.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class FolderAdapter extends BaseAdapter<AlbumInfo> {
    private int selectPosition;

    public FolderAdapter(Context context) {
        super(context);
    }

    @Override
    protected int bindLayout() {
        return R.layout.adapter_floder;
    }

    @Override
    protected void bindHolder(@NonNull BaseViewHolder holder, int i) {
        AlbumInfo info = getItem(i);
        ImageView iv = holder.getView(R.id.iv_cover);
        ImageView ivSelect = holder.getView(R.id.iv_select);
        ivSelect.setVisibility(i == selectPosition ? View.VISIBLE : View.GONE);
        if (info.getPathList().size() > 0) {
            Glide.with(context).load(info.getPathList().get(0)).into(iv);
        }
        View viewLine = holder.getView(R.id.view_line);
        viewLine.setVisibility(i == getItemCount() - 1 ? View.GONE : View.VISIBLE);
        holder.setText(R.id.tv_title, info.getName());
        holder.setText(R.id.tv_count, "(" + info.getCount() + ")");

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int position = (int) v.getTag();
        if (selectPosition != position) {
            notifyItemChanged(selectPosition);
            notifyItemChanged(position);
        }
        selectPosition = position;
    }
}
