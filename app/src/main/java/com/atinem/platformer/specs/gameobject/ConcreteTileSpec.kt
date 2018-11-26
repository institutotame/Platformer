package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class ConcreteTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "concrete",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)