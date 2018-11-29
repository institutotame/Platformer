package com.atinem.platformer.components

import com.atinem.platformer.Transform

interface UpdateComponent {
    fun update(fps: Long, transform: Transform, playerTransform: Transform)
}