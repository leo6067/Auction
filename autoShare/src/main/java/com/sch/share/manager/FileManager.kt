package com.sch.share.manager

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.sch.share.utils.FileUtil
import java.io.File
import java.io.FileOutputStream
import kotlin.math.log

/**
 * Created by StoneHui on 2019-11-28.
 * <p>
 * 文件管理
 */
object FileManager {

    /**
     * 文件临时保存目录。
     */
    fun getTmpFileDir(context: Context): String {
        val parent = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val child = "${context.packageName}${File.separator}shareTmp"
        return File(parent, child)
                .run {
                    if (!exists()) {
                        mkdirs()
                    }
                    absolutePath
                }
    }

    /**
     * 清理临时文件。
     */
    fun clearTmpFile(context: Context) {
        var where=MediaStore.Audio.Media.DATA+" like \""+getTmpFileDir(context)+"%"+"\"";
        context.contentResolver.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,where,null);

//        val fileDir = File(getTmpFileDir(context))
//        // 通知相册删除图片。
//        fileDir.listFiles().forEach {
//            context.contentResolver.delete(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    MediaStore.Images.Media.DATA + "=?",
//                    arrayOf(it.absolutePath))
//        }
//
//        //删除图片文件。
//        FileUtil.clearDir(fileDir)
    }

    /**
     * 清理临时文件。(视频)
     */
    fun clearTmpFile_Video(context: Context,path:String) {
        var where=MediaStore.Audio.Media.DATA+" like \""+path+"%"+"\"";
        context.contentResolver.delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,where,null);
    }

    /**
     * 保存图片并返回对应的 [File] 对象。
      */
    fun saveBitmap(context: Context, bitmap: Bitmap): File {
        val path = "${getTmpFileDir(context)}${File.separator}${System.currentTimeMillis()}.jpg"
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(path))
        return File(path)
    }
}

