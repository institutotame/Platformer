package com.atinem.platformer.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import com.atinem.platformer.Animator
import com.atinem.platformer.BitmapStore
import com.atinem.platformer.Camera
import com.atinem.platformer.Transform
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class AnimatedGraphicsComponent : GraphicsComponent {

    var bitmapName: String = ""
    lateinit var animator: Animator
    var sectionToDraw: Rect? = null

    override fun initialize(context: Context, spec: GameObjectSpec, objectSize: PointF, pixelsPerMetre: Int) {
        animator = Animator(objectSize.y, objectSize.x, spec.framesAnimation, pixelsPerMetre)

        val totalWidth = objectSize.x * spec.framesAnimation

        bitmapName = spec.bitmapName
        BitmapStore.addBitmap(context,bitmapName, PointF(totalWidth, objectSize.y), pixelsPerMetre, true)

        sectionToDraw = animator.getCurrentFrame(System.currentTimeMillis())
    }

    override fun draw(canvas: Canvas, paint: Paint, transform: Transform, camera: Camera) {
        if(transform.headingRight || transform.headingLeft || transform.speed == 0f){
            sectionToDraw = animator.getCurrentFrame(System.currentTimeMillis())
        }

        val screenCoordinates = camera.worldToScreen(
            transform.location.x,
            transform.location.y,
            transform.getSize().x,
            transform.getSize().y)

        if(transform.facingRight){
            canvas.drawBitmap(BitmapStore.getBitmap(bitmapName), sectionToDraw, screenCoordinates, paint)
        }else{
            canvas.drawBitmap(BitmapStore.getBitmapReversed(bitmapName), sectionToDraw, screenCoordinates, paint)
        }

    }
}