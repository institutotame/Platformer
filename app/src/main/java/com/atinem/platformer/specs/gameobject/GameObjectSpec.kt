package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

abstract class GameObjectSpec(
    val tag: String,
    val bitmapName: String,
    val speed: Float,
    val size: PointF,
    val components: Array<Components>,
    val framesAnimation: Int
)