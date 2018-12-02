package com.atinem.platformer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.PointF

object BitmapStore {

    val mBitmapsMap: MutableMap<String,Bitmap> = mutableMapOf()
    val mBitmapsReversedMap: MutableMap<String,Bitmap> = mutableMapOf()
    lateinit var defaultBitmap: Bitmap

    operator fun invoke(context: Context): BitmapStore{
        addBitmap(context, "death_visible", PointF(1f,1f),128,true)
        defaultBitmap = BitmapFactory.decodeResource(
            context.resources,
            context.resources.getIdentifier("death_visible", "drawable", context.packageName))
        return this
    }

    fun getBitmap(bitmapName: String): Bitmap?{
        return mBitmapsMap[bitmapName]
    }

    fun getBitmapReversed(bitmapName: String): Bitmap?{
        return  mBitmapsReversedMap[bitmapName]
    }

    fun addBitmap(context: Context, bitmapName: String, objectSize: PointF, pixelsPerMetre: Int, needReversed: Boolean){
        if(!mBitmapsMap.containsKey(bitmapName)){
            val resID = context.resources.getIdentifier(bitmapName, "drawable", context.packageName)
            val baseBitmap = BitmapFactory.decodeResource(context.resources, resID)
            val bitmap = Bitmap.createScaledBitmap(baseBitmap,
                (objectSize.x * pixelsPerMetre).toInt(),
                (objectSize.y * pixelsPerMetre).toInt(),
                false)
            mBitmapsMap[bitmapName] = bitmap
            if(needReversed){
                val matrix = Matrix()
                matrix.setScale(-1f,1f)
                val bitmapReversed = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
                mBitmapsReversedMap[bitmapName] = bitmapReversed
            }
        }
    }

    fun clearStore(){
        mBitmapsMap.clear()
        mBitmapsReversedMap.clear()
    }
}