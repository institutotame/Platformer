package com.atinem.platformer.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import android.util.Log
import com.atinem.platformer.BackgroundTransform
import com.atinem.platformer.BitmapStore
import com.atinem.platformer.Camera
import com.atinem.platformer.Transform
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class BackgroundGraphicsComponent : GraphicsComponent {
    var bitmapName: String = ""

    override fun initialize(context: Context, spec: GameObjectSpec, objectSize: PointF, pixelsPerMetre: Int) {
        bitmapName = spec.bitmapName

        BitmapStore.addBitmap(context,bitmapName,objectSize,pixelsPerMetre,true)
    }

    override fun draw(canvas: Canvas, paint: Paint, transform: Transform, camera: Camera) {
        val backgroundTransform = transform as BackgroundTransform

        val bitmap = BitmapStore.getBitmap(bitmapName)
        val bitmapReversed = BitmapStore.getBitmapReversed(bitmapName)

        //Log.d("BackgroundGraphics", "${bitmap.width} x ${bitmap.height}")

        val scaledXClip = (backgroundTransform.xClip * camera.pixelsPerMetre).toInt()

        bitmap?.let {
            val width = bitmap.width
            val height = bitmap.height
            val position = transform.location

            val floatStartY  = ((camera.screenCentreY - ((camera.currentCameraWorldCentre.y - position.y) * camera.pixelsPerMetre)))
            val startY = floatStartY.toInt()

            val floatEndY = ((camera.screenCentreY - ((camera.currentCameraWorldCentre.y - position.y - transform.getSize().y) * camera.pixelsPerMetre)))
            val endY = floatEndY.toInt()

            val fromRect1 = Rect(0,0,width - scaledXClip, height)
            val toRect1 = Rect(scaledXClip, startY, width, endY)

            val fromRect2 = Rect(width - scaledXClip, 0, width, height)
            val toRect2 = Rect(0, startY, scaledXClip, endY)

            if(!backgroundTransform.reversedFirst){
                canvas.drawBitmap(bitmap, fromRect1, toRect1, paint)
                canvas.drawBitmap(bitmapReversed, fromRect2, toRect2, paint)
            }else{
                canvas.drawBitmap(bitmap, fromRect2, toRect2, paint)
                canvas.drawBitmap(bitmapReversed, fromRect1, toRect1, paint)
            }
        }


    }
}