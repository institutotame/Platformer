package com.atinem.platformer.components

import com.atinem.platformer.Transform

class InanimateBlockUpdateComponent : UpdateComponent {
    private var colliderNotSet = true

    override fun update(fps: Long, transform: Transform, playerTransform: Transform) {
        if(colliderNotSet){
            transform.updateCollider()
            colliderNotSet = false
        }
    }
}