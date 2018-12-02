package com.atinem.platformer.components

import com.atinem.platformer.Transform

class MovableBlockUpdateComponent : UpdateComponent {
    override fun update(fps: Long, transform: Transform, playerTransform: Transform) {
        val location = transform.location

        when {
            transform.headingUp -> location.y -= transform.speed / fps
            transform.headingDown -> location.y += transform.speed / fps
            else -> transform.headDown()
        }

        if(transform.headingUp && location.y <= transform.startingPosition.y){
            transform.headDown()
        }else if(transform.headingDown && location.y >= (transform.startingPosition.y + transform.getSize().y * 10)){
            transform.headUp()
        }
        transform.updateCollider()
    }
}