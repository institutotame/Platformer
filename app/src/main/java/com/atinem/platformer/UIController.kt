package com.atinem.platformer

import android.graphics.PointF
import android.graphics.Rect
import android.view.MotionEvent
import com.atinem.platformer.components.GameEngineBroadcaster
import com.atinem.platformer.components.InputObserver

class UIController(gameEngineBroadcaster: GameEngineBroadcaster, size: PointF): InputObserver {
    private val third: Float

    private var initialPress = false

    init {
        addObserver(gameEngineBroadcaster)

        third = size.x / 3
    }

    fun addObserver(gameEngineBroadcaster: GameEngineBroadcaster){
        gameEngineBroadcaster.addObserver(this)
    }

    override fun handleInput(event: MotionEvent, gameState: GameState, buttons: List<Rect>) {
        val i = event.actionIndex
        val x = event.getX(i)
        val eventType = event.action and MotionEvent.ACTION_MASK

        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP){
            if(gameState.gameOver && initialPress){
                if(x < third){
                    gameState.currentLevel = "underground"
                    gameState.startNewGame()
                }else if(x >= third && x < third * 2){
                    gameState.currentLevel = "mountains"
                    gameState.startNewGame()
                }else if(x >= third *2){
                    gameState.currentLevel = "city"
                    gameState.startNewGame()
                }
            }
            initialPress = !initialPress
        }
    }
}