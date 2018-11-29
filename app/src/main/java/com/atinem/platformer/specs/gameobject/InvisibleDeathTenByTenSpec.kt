package com.atinem.platformer.specs.gameobject

import android.graphics.PointF
import com.atinem.platformer.components.Components

class InvisibleDeathTenByTenSpec : GameObjectSpec(
    tag = "Death",
    bitmapName = "death_visible",
    speed = 0f,
    size = PointF(10f,10f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)