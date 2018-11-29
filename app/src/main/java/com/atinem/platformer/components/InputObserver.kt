package com.atinem.platformer.components

import android.graphics.Rect
import android.view.MotionEvent

interface InputObserver {
    fun handleInput(event: MotionEvent, gameState: GameState, buttons: List<Rect>)
}