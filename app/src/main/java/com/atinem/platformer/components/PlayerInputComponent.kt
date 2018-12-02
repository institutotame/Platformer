package com.atinem.platformer.components

import android.graphics.Rect
import android.view.MotionEvent
import com.atinem.platformer.*

class PlayerInputComponent(gameEngine: GameEngine) : InputObserver {

    var playersTransform: Transform? = null
    var playersPlayerTransform: PlayerTransform? = null

    init {
        gameEngine.addObserver(this)
    }

    fun setTransform(transform: Transform){
        playersTransform = transform
        playersPlayerTransform = playersTransform as PlayerTransform
    }

    override fun handleInput(event: MotionEvent, gameState: GameState, buttons: List<Rect>) {
        val i = event.actionIndex
        val x = event.getX(i).toInt()
        val y = event.getY(i).toInt()

        if(!gameState.paused){
            when(event.action and MotionEvent.ACTION_MASK){
                MotionEvent.ACTION_UP -> {
                    if(buttons[HUD.LEFT].contains(x,y) || buttons[HUD.RIGHT].contains(x,y)){
                        playersTransform?.stopHorizontal()
                    }
                }
                MotionEvent.ACTION_DOWN -> {
                    when {
                        buttons[HUD.LEFT].contains(x,y) -> playersTransform?.headLeft()
                        buttons[HUD.RIGHT].contains(x,y) -> playersTransform?.headRight()
                        buttons[HUD.JUMP].contains(x,y) -> playersPlayerTransform?.triggerJump()
                    }
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    if(buttons[HUD.LEFT].contains(x,y) || buttons[HUD.RIGHT].contains(x,y)){
                        playersTransform?.stopHorizontal()
                    }
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    when {
                        buttons[HUD.LEFT].contains(x,y) -> playersTransform?.headLeft()
                        buttons[HUD.RIGHT].contains(x,y) -> playersTransform?.headRight()
                        buttons[HUD.JUMP].contains(x,y) -> playersPlayerTransform?.triggerJump()
                    }
                }
            }
        }
    }
}