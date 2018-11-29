package com.atinem.platformer.specs.gameobject

import android.graphics.PointF
import com.atinem.platformer.components.Components

class DeadTreeTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "dead_tree",
    speed = 0f,
    size = PointF(2f,4f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.DecorativeBlockUpdateComponent),
    framesAnimation = 1
)