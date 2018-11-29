package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class StonePileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "stone_pile",
    speed = 0f,
    size = PointF(2f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)