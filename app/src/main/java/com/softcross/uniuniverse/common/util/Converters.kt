package com.softcross.uniuniverse.common.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        Log.e("Bitmap size", outputStream.toByteArray().size.toString())
        return compressImage(outputStream.toByteArray())
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


    private fun compressImage(imageToCompress: ByteArray): ByteArray {
        var compressImage = imageToCompress
        while (compressImage.size > 500000) {
            val bitmap = toBitmap(compressImage)
            val resized = Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width * 0.8).toInt(),
                (bitmap.height * 0.8).toInt(),
                true
            )
            val outputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            compressImage = outputStream.toByteArray()
        }
        Log.e("Compress image size", compressImage.size.toString())
        return compressImage
    }

}