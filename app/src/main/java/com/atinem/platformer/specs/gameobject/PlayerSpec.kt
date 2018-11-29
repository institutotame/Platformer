package com.atinem.platformer.specs.gameobject

import android.graphics.PointF

class PlayerSpec : GameObjectSpec(
    tag = "Player",
    bitmapName = "player",
    speed = 10f,
    size = PointF(1f,2f),
    components = arrayOf(
        Components.PlayerInputComponent,
        Components.AnimatedGraphicsComponent,
        Components.PlayerUpdateComponent),
    framesAnimation = 5
)