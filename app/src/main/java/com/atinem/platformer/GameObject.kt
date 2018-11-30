package com.atinem.platformer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.atinem.platformer.components.GraphicsComponent
import com.atinem.platformer.components.UpdateComponent
import com.atinem.platformer.specs.gameobject.GameObjectSpec

class GameObject {

    var transform: Transform? = null

    var isActive : Boolean = true
    var tag : String = ""

    var graphicsComponent: GraphicsComponent? = null
    var updateComponent: UpdateComponent? = null

    fun setGraphics(graphicsComponent: GraphicsComponent,
                    context: Context,
                    spec: GameObjectSpec,
                    objectSize: PointF,
                    pixelsPerMetre: Int
    ){
        this.graphicsComponent = graphicsComponent
        graphicsComponent.initialize(context,spec,objectSize,pixelsPerMetre)
    }

    fun setMovement(updateComponent: UpdateComponent){
        this.updateComponent = updateComponent
    }

    /*fun setPlayerInputTransform(playerInputComponent: PlayerInputComponent){
        playerInputComponent.setTransform(transform)
    }*/

    fun draw(canvas: Canvas, paint: Paint, camera: Camera){
        transform?.let {
            graphicsComponent?.draw(canvas,paint,it,camera)
        }
    }

    fun update(fps: Long, playerTransform: Transform){
        transform?.let {
            updateComponent?.update(fps,it,playerTransform)
        }
    }

}