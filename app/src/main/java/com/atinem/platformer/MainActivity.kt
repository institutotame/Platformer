package com.atinem.platformer

import android.graphics.Point
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.toPoint
import com.atinem.platformer.specs.gameobject.BackgroundCitySpec

class MainActivity : AppCompatActivity() {

    lateinit var gameEngine: GameEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val display = windowManager.defaultDisplay
        val size = PointF()
        display.getSize(size.toPoint())

        gameEngine = GameEngine(this,size)
        setContentView(gameEngine)
    }

    override fun onResume() {
        super.onResume()
        gameEngine.startJob()
    }

    override fun onPause() {
        super.onPause()
        gameEngine.stopJob()
    }
}
