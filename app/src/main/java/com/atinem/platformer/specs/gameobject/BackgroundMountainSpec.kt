package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class BackgroundMountainSpec() : GameObjectSpec(
    tag = "Background",
    bitmapName = "mountain",
    speed = 3f,
    size = PointF(100f,70f),
    components = arrayOf(
        Components.BackgroundGraphicsComponent,
        Components.BackgroundUpdateComponent),
    framesAnimation = 1
)