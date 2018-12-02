package com.atinem.platformer

import android.graphics.RectF
import android.util.Log

class PhysicsEngine {
    fun update(fps: Long, gameObjects: List<GameObject>, gameState: GameState){
        for(gameObject in gameObjects){
            gameObject.update(fps, gameObjects[LevelManager.PLAYER_INDEX].transform)
        }

        detectCollisions(gameState, gameObjects)
    }

    fun detectCollisions(gameState: GameState, gameObjects: List<GameObject>){
        var collisionOcurred = false

        val playersTransform = gameObjects[LevelManager.PLAYER_INDEX].transform

        val playersPlayerTransform = playersTransform as PlayerTransform

        val playerColliders = playersPlayerTransform.getColliders()

        val playerLocation = playersPlayerTransform.location

        for(gameObject in gameObjects){
            if(gameObject.isActive){
                if(RectF.intersects(gameObject.transform?.collider, playersPlayerTransform.collider)){
                    val testedTransform = gameObject.transform
                    if(gameObjects.indexOf(gameObject) != LevelManager.PLAYER_INDEX){
                        collisionOcurred = true
                        for(i in 0 until playerColliders.size){
                            if(RectF.intersects(testedTransform?.collider, playerColliders[i])){
                                when("${gameObject.tag} with $i"){
                                    "Movable Platform with 0" -> {
                                        playersPlayerTransform.setGrounded()
                                        testedTransform?.let {
                                            playerLocation.y = it.location.y - playersPlayerTransform.getSize().y
                                        }
                                    }
                                    "Death with 0" -> gameState.death()
                                    "Inert Tile with 0" -> {
                                        playersPlayerTransform.setGrounded()
                                        testedTransform?.let {
                                            playerLocation.y = it.location.y - playersPlayerTransform.getSize().y
                                        }
                                    }
                                    "Inert Tile with 1" -> {
                                        testedTransform?.let {
                                            playerLocation.y = it.location.y + it.getSize().y
                                            playersPlayerTransform.triggerBumpedHead()
                                        }
                                    }
                                    "Collectible with 2" -> {
                                        collisionWithCollectible(gameObject,gameState)
                                    }
                                    "Inert Tile with 2" -> {
                                        playersPlayerTransform.stopMovingRight()
                                        testedTransform?.let {
                                            playerLocation.x = it.location.x - playersPlayerTransform.getSize().x
                                        }
                                    }
                                    "Collectible with 3" -> {
                                        collisionWithCollectible(gameObject, gameState)
                                    }
                                    "Inert Tile with 3" -> {
                                        playersPlayerTransform.stopMovingLeft()
                                        testedTransform?.let{
                                            playerLocation.x = it.location.x + it.getSize().x
                                        }
                                    }
                                    "Objective Tile with 0" -> {
                                        SoundEngine.playReachObjective()
                                        gameState.objectiveReached()
                                    }
                                    else -> {
                                        Log.d("Physics", "Not colliding")
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        if(!collisionOcurred){
            playersPlayerTransform.setNotGrounded()
        }
    }

    fun collisionWithCollectible(gameObject: GameObject, gameState: GameState){
        SoundEngine.playCoinPickup()
        gameObject.isActive = false
        gameState.coinCollected()
    }
}