package com.atinem.platformer

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.view.SurfaceHolder
import com.atinem.Camera
import com.atinem.GameObject

class Renderer(val surfaceHolder: SurfaceHolder, screenSize: PointF) {
    val paint = Paint()
    val camera = Camera(screenSize.x, screenSize.y)

    fun getPixelsPerMetre() = camera.pixelsPerMetre

    fun draw(gameObjects: List<GameObject>, gameState: GameState, hud: HUD){
        if(surfaceHolder.surface.isValid){
            val canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.argb(255,0,0,0))

            if(gameState.drawing){
                camera.setWorldCentre(gameObjects[LevelManager.PLAYER_INDEX].transform.location)

                for(gameObject in gameObjects){
                    if(gameObject.isActive){
                        gameObject.draw(canvas,paint,camera)
                    }
                }
            }
            hud.draw(canvas,paint,gameState)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }
}