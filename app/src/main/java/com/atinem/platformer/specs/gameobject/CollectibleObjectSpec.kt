package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class CollectibleObjectSpec : GameObjectSpec(
    tag = "Collectible",
    bitmapName = "coin",
    speed = 0f,
    size = PointF(1f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.InanimateBlockUpdateComponent),
    framesAnimation = 1
)