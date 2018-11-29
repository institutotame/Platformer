package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class SnowyTreeTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "snowy_tree",
    speed = 0f,
    size = PointF(3f,6f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.DecorativeBlockUpdateComponent),
    framesAnimation = 1
)