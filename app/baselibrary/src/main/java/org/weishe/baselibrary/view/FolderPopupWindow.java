package org.weishe.baselibrary.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.weishe.baselibrary.AlbumInfo;
import org.weishe.baselibrary.R;
import org.weishe.baselibrary.adapter.BaseAdapter;
import org.weishe.baselibrary.adapter.FolderAdapter;

import java.util.List;


public class FolderPopupWindow extends PopupWindow implements BaseAdapter.ItemClick {

    private static final int DEFAULT_IMAGE_FOLDER_SELECT = 0;//默认选中文件夹

    private Context mContext;

    private FolderAdapter adapter;
    // 第一个相册item实体，也就是全部图片

    public FolderPopupWindow(Context context) {
        this.mContext = context;
        initView();

    }

    public void setData(List<AlbumInfo> albumInfoList) {
        adapter.addItem(albumInfoList);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.window_image_folders, null);
        RecyclerView rvFolder = view.findViewById(R.id.rv_folder);
        rvFolder.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new FolderAdapter(mContext);
        adapter.addItemClick(this);
        rvFolder.setAdapter(adapter);
        initPopupWindow(view);
    }

    /**
     * 初始化PopupWindow的一些属性
     */
    private void initPopupWindow(View view) {
        setContentView(view);
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int[] screenSize = new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
        setWidth(screenSize[0]);
        setHeight((int) (screenSize[1] * 0.6f));
        setBackgroundDrawable(new ColorDrawable());
        setOutsideTouchable(true);
        setFocusable(false);
    }

    public FolderAdapter getAdapter() {
        return adapter;
    }

    // 上次选择的相册
    private int lastPosition;

    @Override
    public void itemClick(int position) {
        if (position == lastPosition) {
            dismiss();
            return;
        }
        lastPosition = position;
        if (listener != null) {
            listener.select(position, adapter.getItem(position));
        }
        dismiss();
    }

    private AlbumSelectListener listener;

    public void setAlbumSelectListener(AlbumSelectListener listener) {
        this.listener = listener;
    }


    public interface AlbumSelectListener {
        void select(int position, AlbumInfo info);
    }
}
