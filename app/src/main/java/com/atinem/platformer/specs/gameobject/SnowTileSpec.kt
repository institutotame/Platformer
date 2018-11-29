package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class SnowTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "snow",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)