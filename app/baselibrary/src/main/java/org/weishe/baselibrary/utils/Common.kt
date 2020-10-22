package org.weishe.baselibrary.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import java.util.*

private val TAG = "comLibLog"
private var debug = true

fun log(msg: String, tag: String = TAG) {
    if (debug) {
        Log.d(tag, msg)
    }
}

fun loge(msg: String, tag: String = TAG) {
    if (debug) {
        Log.e(tag, msg)
    }
}

fun toast(context: Context,msg: String, longLength: Boolean = false) {
    Toast.makeText(context, msg,
        if (longLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

fun tryCatch(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 打开指定App页面，如果mainClassPath为空，则仅打开App
 *
 * @param activity
 * @param thirdPackageName
 * @param mainClassPath
 * @param data
 */
fun openApp(
    activity: Activity,
    thirdPackageName: String?,
    mainClassPath: String?,
    data: String
): Boolean {
    try {
        if (!TextUtils.isEmpty(mainClassPath)) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.component = ComponentName(thirdPackageName!!, mainClassPath!!)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        } else {
            if (!TextUtils.isEmpty(data) && data.startsWith("http")) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.setPackage(thirdPackageName)
                intent.data = Uri.parse(data)
                activity.startActivity(intent)
            } else {
                val intent =
                    activity.packageManager.getLaunchIntentForPackage(thirdPackageName!!)
                if (intent != null) {
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    activity.startActivity(intent)
                }
            }
        }
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        log("HomeFragment", "手机上未安装该应用")
    }
    return false
}

/**
 * @param context
 * @param url
 */
fun openBrowser(context: Context, url: String) {
    log(TAG, "url是——>$url")
    try {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (checkAppExisted(context, "com.tencent.mtt")) { //qq浏览器
            log(TAG, "openBrowser 使用qq浏览器打开")
            intent.setClassName("com.tencent.mtt", "com.tencent.mtt.MainActivity") //打开QQ浏览器
        }
        context.startActivity(intent)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}


fun checkAppExisted(
    context: Context,
    packageName: String?
): Boolean {
    if (packageName == null || "" == packageName) {
        return false
    }
    log(TAG, "checkAppExisted $packageName")
    return try {
        val info = context.packageManager
            .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}



/**
 * 通过反射的方式获取状态栏高度
 *
 * @return
 */
fun getStatusBarHeight(context: Context): Int {
    try {
        val c = Class.forName("com.android.internal.R\$dimen")
        val obj = c.newInstance()
        val field = c.getField("status_bar_height")
        val x = field[obj].toString().toInt()
        return context.resources.getDimensionPixelSize(x)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return 0
}

/**
 * 获取版本号
 *
 * @return
 */
fun getVersionName(context: Context): String? {
    val manager = context.packageManager //获取包管理器
    try {
        //通过当前的包名获取包的信息
        val info = manager.getPackageInfo(context.packageName, 0) //获取包对象信息
        return "" + info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 获取版本号
 *
 * @return
 */
fun getVersionCode(context: Context): Int {
    val manager = context.packageManager //获取包管理器
    try {
        //通过当前的包名获取包的信息
        val info = manager.getPackageInfo(context.packageName, 0) //获取包对象信息
        return info.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return -1
}




