package com.basics

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.noob.coroutineretrofit.R
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ImageUploadActivity : AppCompatActivity(), UploadRequest.UploadCallback {
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var root_layout: ConstraintLayout
    private lateinit var uploadButton: Button
    private var imageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        image = findViewById(R.id.image)
        uploadButton = findViewById(R.id.uploadButton)
        progressBar = findViewById(R.id.progressBar)
        root_layout = findViewById(R.id.root_layout)


        image.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                this.type = "image/"
                val extraMem = arrayOf("image/jpeg", "image/png")
                this.putExtra(Intent.EXTRA_MIME_TYPES, extraMem)
                startActivityForResult(this, IMAGE_PICKER)
            }
        }

        uploadButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (imageUri==null){
            root_layout.snackbar("Image Should Not be Null")
            return
        }

        val parseFileDescriptor=contentResolver.openFileDescriptor(imageUri!!,"r")?:return
        val inputStream =FileInputStream(parseFileDescriptor.fileDescriptor)
        val originalFile=File(cacheDir,contentResolver.getFileName(imageUri!!),)
        val outputStream=FileOutputStream(originalFile)
        inputStream.copyTo(outputStream)

        val body=UploadRequest(originalFile,"image",this)
        RetrofitBuilder.apiService.uploadImage("http://192.168.94.2:8012/ImageUploader/Api.php?apicall=upload",
                    MultipartBody.Part.createFormData("image",originalFile.name,body),
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(),"Image to Upload"))
            .enqueue(object :Callback<UploadResponse> {
                override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                    progressBar.progress=100
                    root_layout.snackbar("Image Upload Successfully")
                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    root_layout.snackbar(t.message.toString())
                }

            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                IMAGE_PICKER -> {
                    imageUri = data?.data
                    image.setImageURI(imageUri)
                }
            }
        }
    }

    companion object {
        const val IMAGE_PICKER = 101
    }


    //TODO Extension Function
    fun View.snackbar(message:String){
        Snackbar.make(this,message,Snackbar.LENGTH_INDEFINITE).also { snackBar ->
            snackBar.setAction("ok"){
                snackBar.dismiss()
            }
        }.show()
    }

    @SuppressLint("Range")
    fun ContentResolver.getFileName(uri:Uri): String {
        val name="${System.currentTimeMillis()}"
        val cursor=query(uri,null,null,null,null)
        cursor?.use {
            it.moveToFirst()
            cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }

        return name
    }

    override fun onProgressUpdate(percentages: Int) {
        progressBar.progress=percentages
    }
}