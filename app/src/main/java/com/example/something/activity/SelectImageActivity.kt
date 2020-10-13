package com.example.something.activity

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.something.R
import com.example.something.net_work.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_image.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


/**
 *  create by pan yi on 2020/10/12
 *  desc : 本地相册选择图片
 */
class SelectImageActivity : BaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_select_image
    }

    private val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val codePermission = 101
    private val codeData = 100
    override fun initView() {
        selectPic()
        tv_re_select.setOnClickListener {
            selectPic()
        }
    }

    private fun selectPic() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(permissions, codePermission)
        } else {
            openImageChooserActivity()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == codePermission) {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT)
                        .show()
                    return
                }
            }
            openImageChooserActivity()
        }
    }

    private fun openImageChooserActivity() {
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), codeData)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codeData && resultCode == Activity.RESULT_OK) {
            data?.let {
                val uri: Uri? = data.data
                uri?.let {
                    var path: String? = ""
                    if (DocumentsContract.isDocumentUri(this, uri)) {

                        val docId = DocumentsContract.getDocumentId(uri)
                        if ("com.android.providers.media.documents" == uri.authority) {
                            val id = docId.split(":".toRegex()).toTypedArray()[1]
                            val selection = MediaStore.Images.Media._ID + "=" + id
                            path = getImagePath(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                selection
                            )
                        } else if ("com.android.providers.downloads.documents" == uri.authority) {
                            val contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"),
                                java.lang.Long.valueOf(docId)
                            )
                            path = getImagePath(contentUri, null)
                        }
                    } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                        path = getImagePath(uri, null)
                    } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                        path = uri.path
                    }
                    path?.let {  //
//                        Glide.with(this).load(it).into(iv)
                        compressBitmap(it)
                    }
                }
            }
        }
    }

    //content类型的uri获取图片路径的方法
    private fun getImagePath(uri: Uri, selection: String?): String? {
        var path: String? = null

        val cursor: Cursor? = contentResolver.query(uri, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    private fun compressBitmap(
        srcPath: String,
        ImageSize: Int = 1,
        savePath: String = "Cache"
    ) {
        val filePath: String = getExternalFilesDir(savePath)?.absolutePath ?: return
        val localFile = File(filePath)
        if (!localFile.exists()) {
            localFile.mkdir()
        }
        var subtract: Int
        val bitmap: Bitmap = BitmapFactory.decodeFile(srcPath)
        val baos = ByteArrayOutputStream()
        var options = 100
        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            options,
            baos
        ) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        while (baos.toByteArray().size > ImageSize * 1024 * 1024) { //循环判断如果压缩后图片是否大于ImageSize 单位：M,大于继续压缩
            Log.e(
                TAG,
                "compressBitmap: " + baos.toByteArray().size + " size " + (ImageSize * 1024 * 1024)
            )
            subtract = getSubtractSize(baos.toByteArray().size / 1024)
            baos.reset()
            options -= subtract //每次减少质量
            if (options < 0) {
                options = 1
            }
            if (options > 100) {
                options = 99
            }
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                options,
                baos
            ) //这里压缩options%，把压缩后的数据存放到baos中
        }
        try {
            val realPath = filePath + "/" + System.currentTimeMillis().toString() + ".jpg"
            Log.e(TAG, "compressBitmap: $realPath")
            val fos = FileOutputStream(File(realPath)) //将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray())
            fos.flush()
            fos.close()

            Glide.with(this).load(realPath).into(iv)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bitmap.recycle()
    }

    private fun getSubtractSize(imageKb: Int): Int {
        return when {
            imageKb > 1000 -> {
                10
            }
            imageKb > 750 -> {
                8
            }
            imageKb > 500 -> {
                6
            }
            else -> {
                4
            }
        }
    }

}