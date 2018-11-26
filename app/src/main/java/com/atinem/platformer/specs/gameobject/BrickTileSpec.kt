package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class BrickTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "brick",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)