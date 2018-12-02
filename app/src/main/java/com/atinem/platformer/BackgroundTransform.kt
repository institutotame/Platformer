package com.atinem.platformer

import android.graphics.PointF

class BackgroundTransform(speed: Float, objectWidth: Float, objectHeight: Float, startingLocation: PointF)
    : Transform(speed, objectWidth, objectHeight, startingLocation) {
    var xClip: Float = 0f
    var reversedFirst: Boolean = false

    fun flipReversedFirst(){
        reversedFirst = !reversedFirst
    }
}