package com.atinem.platformer

import android.content.Context
import android.content.SharedPreferences
import com.atinem.platformer.components.EngineController

class GameState(val engineController: EngineController, context: Context) {
    var threadRunning: Boolean = false
        private set
    var paused = true
        private set
    var gameOver = true
        private set
    var drawing = false
        private set

    var fastestUnderground: Int
        private set
    var fastestMountains: Int
        private set
    var fastestCity: Int
        private set

    private var startTimeInMillis: Long = 0

    private var coinsAvailable: Int = 0
    private var coinsCollected: Int = 0


    private val editor: SharedPreferences.Editor

    var currentLevel: String = ""

    init {
        val prefs: SharedPreferences = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE)
        editor = prefs.edit()
        fastestUnderground = prefs.getInt("fastest_underground_time", 1000)
        fastestMountains = prefs.getInt("fastest_mountains_time", 1000)
        fastestCity = prefs.getInt("fastest_city_time", 1000)
    }

    fun coinCollected() = coinsCollected++

    fun getCoinsRemaining(): Int = coinsAvailable - coinsCollected

    fun coinAddedToLevel() = coinsAvailable++

    fun resetCoins(){
        coinsAvailable = 0
        coinsCollected = 0
    }

    fun objectiveReached() = endGame()

    fun startNewGame() {
        stopEverything()
        engineController.startNewLevel()
        startEveryThing()
        startTimeInMillis = System.currentTimeMillis()
    }

    fun getCurrentTime() = ((System.currentTimeMillis() - startTimeInMillis) / 1000).toInt()

    fun death() {
        stopEverything()
        SoundEngine.playPlayerBurn()
    }

    private fun endGame(){
        stopEverything()
        val totalTime = ((coinsAvailable - coinsCollected) * 10) + getCurrentTime()

        when(currentLevel){
            "underground" -> {
                if(totalTime < fastestUnderground){
                    fastestUnderground = totalTime
                    editor.putInt("fastest_underground_time", fastestUnderground)
                    editor.commit()
                }
            }
            "city" -> {
                if(totalTime < fastestCity){
                    fastestCity = totalTime
                    editor.putInt("fastest_city_time", fastestCity)
                    editor.commit()
                }
            }
            "mountains" -> {
                if(totalTime < fastestMountains){
                    fastestMountains = totalTime
                    editor.putInt("fastest_mountains_time", fastestMountains)
                    editor.commit()
                }
            }
        }
    }

    fun stopEverything(){
        paused = true
        gameOver = true
        drawing = false
    }

    fun startEveryThing(){
        paused = false
        gameOver = false
        drawing = true
    }

    fun stopThread(){
        threadRunning = false
    }

    fun startThread(){
        threadRunning = true
    }

}