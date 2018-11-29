package com.atinem.platformer.specs.gameobject

import android.graphics.PointF
import com.atinem.platformer.components.Components

class CoalTileSpec : GameObjectSpec(
    tag = "Inert Tile",
    bitmapName = "coal",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
) {
}