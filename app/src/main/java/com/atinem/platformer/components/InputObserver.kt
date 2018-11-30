package com.atinem.platformer.components

import android.graphics.RectF
import android.view.MotionEvent
import com.atinem.platformer.GameState

interface InputObserver {
    fun handleInput(event: MotionEvent, gameState: GameState, buttons: List<RectF>)
}