package com.atinem.platformer.components

interface GameEngineBroadcaster {
    fun addObserver(observer: InputObserver)
}