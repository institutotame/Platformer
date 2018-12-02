package com.atinem.platformer.components

import com.atinem.platformer.PlayerTransform
import com.atinem.platformer.SoundEngine
import com.atinem.platformer.Transform

class PlayerUpdateComponent : UpdateComponent {
    var isJumping: Boolean = false
    var jumpStartTime: Long = 0L

    override fun update(fps: Long, transform: Transform, playerTransform: Transform) {
        val mPlayerTransform: PlayerTransform = playerTransform as PlayerTransform

        val location = transform.location
        val speed = transform.speed

        if(transform.headingLeft){
            location.x -= speed / fps
        }else if(transform.headingRight){
            location.x += speed / fps
        }

        if(mPlayerTransform.isBumpedHeadTriggered){
            isJumping = false
            mPlayerTransform.handlingBumpedHead()
        }

        if(mPlayerTransform.isJumpTriggered && !isJumping && mPlayerTransform.isGrounded){
            SoundEngine.playJump()
            isJumping = true
            mPlayerTransform.handlingJump()
            jumpStartTime = System.currentTimeMillis()
        }

        if(!isJumping){
            if(fps>0){
                location.y += GRAVITY / fps
            }
        }else if(isJumping){
            mPlayerTransform.setNotGrounded()
            when {
                System.currentTimeMillis() < jumpStartTime + (MAX_JUMP_TIME / 1.5) -> location.y -= (GRAVITY * 1.8f) / fps
                System.currentTimeMillis() < jumpStartTime + MAX_JUMP_TIME -> location.y += GRAVITY / fps
                System.currentTimeMillis() > jumpStartTime + MAX_JUMP_TIME -> isJumping = false
            }
        }

        transform.updateCollider()
    }

    companion object {
        private const val MAX_JUMP_TIME = 400
        private const val GRAVITY = 6
    }
}