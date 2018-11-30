package com.atinem.platformer

import android.content.Context
import android.graphics.PointF
import com.atinem.platformer.components.Components
import com.atinem.platformer.components.DecorativeBlockUpdateComponent
import com.atinem.platformer.components.InanimateBlockGraphicsComponent
import com.atinem.platformer.components.InanimateBlockUpdateComponent
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class GameObjectFactory(val context: Context, val gameEngine: GameEngine, val pixelsPerMetre: Int) {

    fun create(spec: GameObjectSpec, location: PointF): GameObject {
        val gameObject = GameObject()

        val numComponents = spec.components.size
        gameObject.tag = spec.tag

        when(gameObject.tag){
            else -> gameObject.transform = Transform(spec.speed, spec.size.x,spec.size.y,location)
        }

        for(i in 0 until numComponents){
            when(spec.components[i]){
                // TODO SOON
                Components.InanimateBlockGraphicsComponent -> {
                    gameObject.setGraphics(InanimateBlockGraphicsComponent(), context, spec, spec.size, pixelsPerMetre)
                }
                Components.InanimateBlockUpdateComponent -> {
                    gameObject.setMovement(InanimateBlockUpdateComponent())
                }
                Components.DecorativeBlockUpdateComponent -> {
                    gameObject.setMovement(DecorativeBlockUpdateComponent())
                }
                else ->{

                }
            }
        }

        return gameObject
    }

}