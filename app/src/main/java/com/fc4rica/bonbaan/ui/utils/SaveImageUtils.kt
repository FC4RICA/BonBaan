package com.fc4rica.bonbaan.ui.utils

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

suspend fun saveImageToGallery(context: Context, imageUrl: String, fileName: String = "qrcode_${System.currentTimeMillis()}.jpg") {
    try {
        val client = OkHttpClient()
        val request = Request.Builder().url(imageUrl).build()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val inputStream = response.body?.byteStream()

            if (inputStream != null) {
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/QR Codes")
                }

                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                if (uri != null) {
                    resolver.openOutputStream(uri)?.use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    withContext(Dispatchers.IO) {
                        inputStream.close()
                    }

                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "QR Code saved to Gallery", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to create MediaStore entry", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Failed to download image", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}