package com.atinem.platformer

import android.graphics.PointF
import android.graphics.RectF

open class Transform(val speed: Float, val objectWidth: Float, val objectHeight: Float, startingLocation: PointF) {
    val collider : RectF = RectF()
    var location: PointF = startingLocation
    val startingPosition : PointF = PointF(location.x,location.y)

    var headingUp: Boolean = false
    var headingDown: Boolean = false

    var facingRight: Boolean = true
    var headingLeft: Boolean = false
    var headingRight: Boolean = false

    fun updateCollider(){
        collider.top = location.y
        collider.left = location.x
        collider.bottom = collider.top + objectHeight
        collider.right = collider.left + objectWidth
    }

    fun headUp(){
        headingUp = true
        headingDown = false
    }

    fun headDown(){
        headingDown = true
        headingUp = false
    }

    fun getSize(): PointF = PointF(objectWidth,objectHeight)

    fun headRight(){
        headingRight = true
        headingLeft = false
        facingRight = true
    }

    fun headLeft(){
        headingLeft = true
        headingRight = false
        facingRight = false
    }

    fun stopHorizontal(){
        headingLeft = false
        headingRight = false
    }

    fun stopMovingLeft(){
        headingLeft = false
    }

    fun stopMovingRight(){
        headingRight = false
    }


}