package com.atinem.platformer

import com.atinem.GameObject

class PhysicsEngine {
    fun update(fps: Long, gameObjects: List<GameObject>, gameState: GameState){
        for(gameObject in gameObjects){
            gameObject.update(fps, gameObjects[LevelManager.PLAYER_INDEX].transform)
        }

        detectCollisions(gameState, gameObjects)
    }

    fun detectCollisions(gameState: GameState, gameObjects: List<GameObject>){
        // More code soon
    }
}