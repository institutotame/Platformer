package com.atinem.platformer.specs.gameobject

import android.graphics.PointF
import com.atinem.platformer.components.Components

class FireTileSpec : GameObjectSpec(
    tag = "Death",
    bitmapName = "fire",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.AnimatedGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 3
)