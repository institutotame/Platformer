package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class LamppostTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "lamppost",
    speed = 0f,
    size = PointF(1f,4f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.DecorativeBlockUpdateComponent),
    framesAnimation = 1
)