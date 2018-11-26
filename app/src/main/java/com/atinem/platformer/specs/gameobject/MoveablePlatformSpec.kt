package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class MoveablePlatformSpec : GameObjectSpec(
    tag = "Movable Platform",
    bitmapName = "platform",
    speed = 3f,
    size = PointF(2f,1f),
    components = arrayOf(
        Components.InanimateBlockGraphicsComponent,
        Components.MovableBlockUpdateComponent),
    framesAnimation = 1
)