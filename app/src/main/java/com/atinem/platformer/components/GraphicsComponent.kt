package com.atinem.platformer.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.atinem.Camera
import com.atinem.platformer.Transform
import com.atinem.platformer.specs.gameobject.GameObjectSpec

interface GraphicsComponent {
    fun initialize(context: Context, spec: GameObjectSpec, objectSize: PointF, pixelsPerMetre: Int)

    fun draw(canvas: Canvas, paint: Paint, transform: Transform, camera: Camera)
}