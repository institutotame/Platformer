package com.atinem.platformer

import android.content.Context
import android.graphics.PointF
import com.atinem.platformer.components.*
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class GameObjectFactory(val context: Context, val gameEngine: GameEngine, val pixelsPerMetre: Int) {

    fun create(spec: GameObjectSpec, location: PointF): GameObject {
        val gameObject = GameObject()

        val numComponents = spec.components.size
        gameObject.tag = spec.tag

        when(gameObject.tag){
            "Background" -> {
                gameObject.transform = BackgroundTransform(spec.speed, spec.size.x, spec.size.y, location)
            }
            "Player" -> {
                gameObject.transform = PlayerTransform(spec.speed, spec.size.x, spec.size.y, location)
            }
            else -> gameObject.transform = Transform(spec.speed, spec.size.x,spec.size.y,location)
        }

        for(i in 0 until numComponents){
            when(spec.components[i]){
                Components.PlayerInputComponent -> {
                    gameObject.setPlayerInputTransform(PlayerInputComponent(gameEngine))
                }
                Components.AnimatedGraphicsComponent -> {
                    gameObject.setGraphics(AnimatedGraphicsComponent(), context, spec, spec.size, pixelsPerMetre)
                }
                Components.PlayerUpdateComponent -> {
                    gameObject.setMovement(PlayerUpdateComponent())
                }
                Components.InanimateBlockGraphicsComponent -> {
                    gameObject.setGraphics(InanimateBlockGraphicsComponent(), context, spec, spec.size, pixelsPerMetre)
                }
                Components.InanimateBlockUpdateComponent -> {
                    gameObject.setMovement(InanimateBlockUpdateComponent())
                }
                Components.DecorativeBlockUpdateComponent -> {
                    gameObject.setMovement(DecorativeBlockUpdateComponent())
                }
                Components.BackgroundGraphicsComponent -> {
                    gameObject.setGraphics(BackgroundGraphicsComponent(), context, spec, spec.size, pixelsPerMetre)
                }
                Components.BackgroundUpdateComponent -> {
                    gameObject.setMovement(BackgroundUpdateComponent())
                }
                Components.MovableBlockUpdateComponent -> {
                    gameObject.setMovement(MovableBlockUpdateComponent())
                }
            }
        }

        return gameObject
    }

}