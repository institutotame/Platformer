package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class BackgroundUndergroundSpec() : GameObjectSpec(
    tag = "Background",
    bitmapName = "underground",
    speed = 3f,
    size = PointF(100f,70f),
    components = arrayOf(
        Components.BackgroundGraphicsComponent,
        Components.BackgroundUpdateComponent),
    framesAnimation = 1
)