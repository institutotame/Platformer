package com.atinem.platformer

import android.graphics.PointF
import android.graphics.RectF

class PlayerTransform(
    speed: Float,
    objectWidth: Float,
    objectHeight: Float,
    startingLocation: PointF)
    : Transform(speed, objectWidth, objectHeight, startingLocation)
{

    val feetRectF = RectF()
    val headRectF = RectF()
    val rightRectF = RectF()
    val leftRectF = RectF()

    private val colliders: List<RectF> = listOf(feetRectF, headRectF, rightRectF, leftRectF)


    var isJumpTriggered = false
    var isBumpedHeadTriggered = false

    var isGrounded = false

    fun updateColliders(){
        val location: PointF = location
        val objectHeight: Float = getSize().y
        val objectWidth: Float = getSize().x

        // Feet
        colliders[0].left = location.x + objectWidth * THIRD
        colliders[0].top = location.y + objectHeight - objectHeight * TENTH
        colliders[0].right = location.x + objectWidth - objectWidth * THIRD
        colliders[0].bottom = location.y + objectHeight + objectHeight * FEET_PROTRUSION

        // Head
        colliders[1].left = location.x + objectWidth * THIRD
        colliders[1].top = location.y
        colliders[1].right = location.x + objectWidth - objectWidth * THIRD
        colliders[1].bottom = location.y + objectHeight * TENTH

        // Right
        colliders[2].left = location.x + objectWidth - objectWidth * TENTH
        colliders[2].top = location.y + objectHeight * THIRD
        colliders[2].right = location.x + objectWidth
        colliders[2].bottom = location.y + objectHeight - objectHeight * HALF

        // Left
        colliders[3].left = location.x
        colliders[3].top = location.y + objectHeight * FIFTH
        colliders[3].right = location.x + objectWidth * TENTH
        colliders[3].bottom = location.y + objectHeight - objectHeight * FIFTH
    }

    fun getColliders(): List<RectF>{
        updateColliders()
        return colliders
    }

    fun triggerJump(){
        isJumpTriggered = true
    }

    fun handlingJump() {
        isJumpTriggered = false
    }

    fun setNotGrounded(){
        isGrounded = false
    }

    fun triggerBumpedHead(){
        isBumpedHeadTriggered = true
    }

    fun handlingBumpedHead(){
        isBumpedHeadTriggered = false
    }

    fun setGrounded(){
        isGrounded = true
    }

    companion object {
        private const val TENTH = .1f
        private const val HALF = .5f
        private const val THIRD = .3f
        private const val FIFTH = .2f
        private const val FEET_PROTRUSION = 1.2f
    }
}