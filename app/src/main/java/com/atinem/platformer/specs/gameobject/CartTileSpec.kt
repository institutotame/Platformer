package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class CartTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "cart",
    speed = 0f,
    size = PointF(2f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)