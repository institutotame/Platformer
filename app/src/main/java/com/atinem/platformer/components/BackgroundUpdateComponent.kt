package com.atinem.platformer.components

import com.atinem.platformer.BackgroundTransform
import com.atinem.platformer.PlayerTransform
import com.atinem.platformer.Transform

class BackgroundUpdateComponent : UpdateComponent {
    override fun update(fps: Long, transform: Transform, playerTransform: Transform) {
        val backgroundTransform = transform as BackgroundTransform
        val mPlayerTransform = playerTransform as PlayerTransform

        var currentXClip = backgroundTransform.xClip

        if(playerTransform.headingRight){
            currentXClip -= transform.speed / fps
            backgroundTransform.xClip = currentXClip
        }else if(playerTransform.headingLeft){
            currentXClip += transform.speed / fps
            backgroundTransform.xClip = currentXClip
        }

        if(currentXClip >= transform.getSize().x){
            backgroundTransform.xClip = 0f
            backgroundTransform.flipReversedFirst()
        }else if(currentXClip <= 0){
            backgroundTransform.xClip = transform.getSize().x
            backgroundTransform.flipReversedFirst()
        }
    }
}