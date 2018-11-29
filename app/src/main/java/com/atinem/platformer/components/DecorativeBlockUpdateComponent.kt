package com.atinem.platformer.components

import com.atinem.platformer.Transform

class DecorativeBlockUpdateComponent() : UpdateComponent {
    override fun update(fps: Long, transform: Transform, playerTransform: Transform) {
        // Do nothing
        // Not event set a collider
    }
}