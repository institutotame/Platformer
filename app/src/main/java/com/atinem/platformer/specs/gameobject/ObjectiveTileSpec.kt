package com.atinem.platformer.specs.gameobject

import android.graphics.PointF
import com.atinem.platformer.components.Components

class ObjectiveTileSpec : GameObjectSpec(
    tag = "Objective Tile",
    bitmapName = "objective",
    speed = 0f,
    size = PointF(3f,3f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)