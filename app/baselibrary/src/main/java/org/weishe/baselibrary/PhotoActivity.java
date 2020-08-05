package org.weishe.baselibrary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.weishe.baselibrary.adapter.PhotoAdapter;
import org.weishe.baselibrary.bean.SelectPhotoBean;
import org.weishe.baselibrary.view.FolderPopupWindow;
import org.weishe.baselibrary.view.MyItemDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener, FolderPopupWindow.AlbumSelectListener, PopupWindow.OnDismissListener {
    MyHandler handler;
    RecyclerView rvPhoto;
    PhotoAdapter adapter;
    GridLayoutManager manager;
    int spanCount = 3;
    TextView tvTitle, tvComplete;
    ImageView ivBack;
    FrameLayout flTitle;
    View viewBlank,viewBlank2;
    // 图片文件夹弹窗 进来就初始化
    FolderPopupWindow folderWindow;
    SelectPhotoBean photoBean;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent();
        photoBean = (SelectPhotoBean) getIntent().getSerializableExtra("photoBean");
        setContentView(R.layout.activity_photo);
        setStatusBarColor(this, R.color.white);
        setStatusTextDark(this, true);
        folderWindow = new FolderPopupWindow(this);
        folderWindow.setOnDismissListener(this);
        folderWindow.setAlbumSelectListener(this);

//        folderWindow.setAnimationStyle(R.style.folderAnimator);
        tvTitle = findViewById(R.id.tv_title);
        flTitle = findViewById(R.id.fl_title);
        tvComplete = findViewById(R.id.tv_complete);
        ivBack = findViewById(R.id.iv_back);
        viewBlank = findViewById(R.id.view_blank);
        viewBlank2 = findViewById(R.id.view_blank2);
        ivBack.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
        flTitle.setOnClickListener(this);
        viewBlank.setOnClickListener(this);
        viewBlank2.setOnClickListener(this);
        rvPhoto = findViewById(R.id.rv_photo);
        rvPhoto.addItemDecoration(new MyItemDecoration(this, 4, R.color.white));
        manager = new GridLayoutManager(this, spanCount);
        rvPhoto.setLayoutManager(manager);
        rvPhoto.post(new Runnable() {
            @Override
            public void run() {
                adapter = new PhotoAdapter(PhotoActivity.this, (int) (rvPhoto.getMeasuredWidth() / (float) spanCount));
                adapter.setMaxSelect(photoBean.getMaxPhoto());
                rvPhoto.setAdapter(adapter);
                handler = new MyHandler(PhotoActivity.this);
                checkPermission();
            }
        });

    }

    // 权限
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    int code = 100;

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
                requestPermissions(permissions, code);
            } else {
                ReadPicUrl();
            }
        } else {
            ReadPicUrl();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int result : grantResults) {
            if (result != PERMISSION_GRANTED) {
                Toast.makeText(PhotoActivity.this, "没有权限", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        ReadPicUrl();
    }

    // 图片文件夹信息类
    AlbumInfo allAlbumInfo = new AlbumInfo();

    // 这个线程读取所有的图片，显示
    public void ReadPicUrl() {
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void run() {
                redPicFolder();
                Cursor cursor = getCursor();
                long time = System.currentTimeMillis();
                if (cursor != null) {
                    cursor.moveToLast();
                    addNew(cursor, photoCount);
                    while (cursor.moveToPrevious()) {
                        if (isDestroy) {
                            break;
                        }
                        addNew(cursor, photoSingle);
                    }
                    time = System.currentTimeMillis() - time;
                    Log.e("Time end", "queryMedia: " + time);
                    cursor.close();
                }
            }
        }.start();
    }

    Map<Integer, AlbumInfo> albumMap = new HashMap<>();

    // 这个线程按相册分类读取图片
    public void redPicFolder() {
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void run() {
                Cursor cursor = getCursor();
                long time = System.currentTimeMillis();
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        if (isDestroy) {
                            break;
                        }
                        int folderId = cursor.getInt(cursor.getColumnIndex("bucket_id"));
                        String folderName = cursor.getString(cursor.getColumnIndex("bucket_display_name"));
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        if (albumMap.get(folderId) == null) {
                            AlbumInfo info = new AlbumInfo();
                            info.setName(folderName);
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(path);
                            info.setPathList(arrayList);
                            info.setCount(arrayList.size());
                            albumMap.put(folderId, info);
                        } else {
                            AlbumInfo info = albumMap.get(folderId);
                            assert info != null;
                            info.getPathList().add(0, path);
                            info.setCount(info.getPathList().size());
                        }
                    }
                    Message msg = new Message();
                    msg.what = album;
                    handler.sendMessage(msg);
                    time = System.currentTimeMillis() - time;
                    Log.e("Time end folder", "queryMedia: " + time);
                    cursor.close();
                }
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Cursor getCursor() {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN};
        String[] select = new String[]{"image/jpeg", "image/png"};
        String selection = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?"
                + " or " + MediaStore.Images.Media.MIME_TYPE
                + "=?";
        ContentResolver mContentResolver = getContentResolver();
        // 只查询jpeg和png的图片
        String order = MediaStore.Images.Media.DATE_MODIFIED;
        return mContentResolver.query(mImageUri, projection, selection, select, order);
    }


    // 获取图片的路径， photoSingle为每一张查到的图，photoCount为统计图片数量
    private void addNew(Cursor cursor, int type) {
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        if (allAlbumInfo.getPathList().size() == 0) {
            ArrayList<String> pathList = new ArrayList<>();
            pathList.add(path);
            allAlbumInfo.setPathList(pathList);
        } else {
            allAlbumInfo.getPathList().add(path);
        }
        // 添加扫描到的图片到所有图片显示
        Message msg = new Message();
        msg.what = photoSingle;
        msg.obj = path;
        handler.sendMessage(msg);
        if (type == photoCount) {
            allAlbumInfo.setName("全部图片");
            allAlbumInfo.setCount(cursor.getCount());
            albumInfoList.add(0, allAlbumInfo);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_complete) {
            intent.putStringArrayListExtra("data", adapter.getData());
            finish();
        } else if (id == R.id.fl_title) {
            showPopWindow();
        } else if (id == R.id.iv_back) {
            finish();
        }else if (id==R.id.view_blank){
            viewBlank.setVisibility(View.GONE);
        }else if (id==R.id.view_blank2){
            viewBlank.setVisibility(View.GONE);
            viewBlank2.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        setResult(RESULT_OK, intent);
        super.finish();
    }


    private void showPopWindow() {
        viewBlank.setVisibility(View.VISIBLE);
        viewBlank2.setVisibility(View.VISIBLE);
        folderWindow.showAsDropDown(tvTitle);
    }

    @Override
    public void onDismiss() {
//        if (viewBlank.getVisibility()== View.VISIBLE) {
//            viewBlank.setVisibility(View.GONE);
//        }
    }

    //    photoSingle为每一张查到的图，photoCount为统计图片数量，album为相册
    final static int photoCount = 0, photoSingle = 1, album = 2;

    // 相册的选择
    @Override
    public void select(int position, AlbumInfo info) {
        adapter.clearAllItem();
        adapter.addItem(info.getPathList());
        tvTitle.setText(info.getName());
    }


    private static class MyHandler extends Handler {
        WeakReference<PhotoActivity> weakReference;

        MyHandler(PhotoActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (weakReference != null && weakReference.get() != null) {
                switch (msg.what) {
                    case photoSingle:
                        weakReference.get().addImage((String) msg.obj);
                        break;
                    case album:
                        weakReference.get().setAlbum();
                        break;
                }
            } else {
                Log.e("TAG", "null");
            }
        }
    }

    // 相册数据
    List<AlbumInfo> albumInfoList = new ArrayList<>();

    private void setAlbum() {
        if (albumMap != null) {
            for (Integer integer : albumMap.keySet()) {
                albumInfoList.add(albumMap.get(integer));
            }
            folderWindow.setData(albumInfoList);
        }
    }

    // 添加全部的图片
    private void addImage(String path) {
        adapter.addItem(path);
    }


    boolean isDestroy;

    @SuppressLint("InlinedApi")
    public void setStatusTextDark(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        View content = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        if (content != null) {
            content.setFitsSystemWindows(true);
        }
    }

    //    设置状态栏颜色
    public void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(statusColor));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        handler.removeCallbacksAndMessages(null);
        handler.weakReference.clear();
        handler.weakReference = null;
    }
}
