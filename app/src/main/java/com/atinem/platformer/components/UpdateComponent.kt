package com.atinem.platformer.components

interface UpdateComponent {
    fun update(fps: Long, transform: Transform, playerTransform: Transform)
}