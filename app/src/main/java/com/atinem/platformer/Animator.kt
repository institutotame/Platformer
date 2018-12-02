package com.atinem.platformer

import android.graphics.Rect

class Animator(frameHeight: Float, frameWidth: Float, val frameCount: Int, pixelsPerMetre: Int) {



    var currentFrame: Int = 0
    var frameTicker: Long = 0L

    val framePeriod: Int = 1000 / ANIM_FPS
    val frameWidth: Int = frameWidth.toInt() * pixelsPerMetre
    val sourceRect: Rect = Rect(0,0,this.frameWidth,frameHeight.toInt() * pixelsPerMetre)


    fun getCurrentFrame(time: Long): Rect{
        if(time > frameTicker + framePeriod){
            frameTicker = time
            currentFrame++
            if(currentFrame >= frameCount){
                currentFrame = 0
            }
        }

        // Update the left and right values of the source of the next frame on the spritesheet
        sourceRect.left = currentFrame * frameWidth
        sourceRect.right = sourceRect.left + frameWidth
        return sourceRect
    }

    companion object {
        private const val ANIM_FPS = 10
    }

}