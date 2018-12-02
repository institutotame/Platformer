package com.atinem.platformer.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import com.atinem.platformer.Camera
import com.atinem.platformer.BitmapStore
import com.atinem.platformer.Transform
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class InanimateBlockGraphicsComponent : GraphicsComponent {

    var bitmapName: String = ""

    override fun initialize(context: Context, spec: GameObjectSpec, objectSize: PointF, pixelsPerMetre: Int) {
        bitmapName = spec.bitmapName
        Log.d("INITIALIZEINANIMATE", bitmapName)
        BitmapStore.addBitmap(context,bitmapName,objectSize,pixelsPerMetre,false)
    }

    override fun draw(canvas: Canvas, paint: Paint, transform: Transform, camera: Camera) {
        val bitmap = BitmapStore.getBitmap(bitmapName)

        val screenCoordinates: RectF = camera.worldToScreen(
            transform.location.x,
            transform.location.y,
            transform.getSize().x,
            transform.getSize().y
        )

        Log.d(bitmapName, "${screenCoordinates.width()} x ${screenCoordinates.height()}")

        if(bitmap!=null){
            canvas.drawBitmap(bitmap,screenCoordinates.left,screenCoordinates.top,paint)
        }else{
            Log.d("INANIMATEERROR", "No bitmap")
        }

    }
}