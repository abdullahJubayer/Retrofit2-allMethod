package com.basics

import android.os.Handler
import android.os.Looper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

class UploadRequest(private val file:File,private val contentType:String,private val uploadCallback: UploadCallback) :
    RequestBody() {


    interface UploadCallback{
        fun onProgressUpdate(percentages:Int)
    }

    inner class ProgressUpdate(private val upload: Long, private val length: Long) :Runnable{
        override fun run() {
            uploadCallback.onProgressUpdate((100*upload/length).toInt())
        }

    }

    override fun contentType() = "$contentType/*".toMediaTypeOrNull()

    override fun writeTo(sink: BufferedSink) {
        val length=file.length()
        val buffer=ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream= FileInputStream(file)
        var upload=0L

        fileInputStream.use { inputStream ->
            var read:Int
            val handler= Handler(Looper.getMainLooper())

            while (fileInputStream.read(buffer).also { read = it } != -1){
                handler.post(ProgressUpdate(upload,length))
                upload+=read
                sink.write(buffer,0,read)
            }
        }

    }

    companion object{
        const val DEFAULT_BUFFER_SIZE=1048
    }
}