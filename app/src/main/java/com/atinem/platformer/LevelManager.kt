package com.atinem.platformer

import android.content.Context
import android.graphics.PointF
import android.util.Log
import com.atinem.platformer.levels.Level
import com.atinem.platformer.levels.LevelCity
import com.atinem.platformer.levels.LevelMountains
import com.atinem.platformer.levels.LevelUnderground
import com.atinem.platformer.specs.gameobject.*

class LevelManager(context: Context, gameEngine: GameEngine, pixelsPerMetre: Int) {
    companion object {
        var PLAYER_INDEX = 0
    }

    val objects: MutableList<GameObject> = mutableListOf()
    var currentLevel: Level? = null


    val factory: GameObjectFactory = GameObjectFactory(context,gameEngine,pixelsPerMetre)

    fun setCurrentLevel(level: String){
        when(level){
            "underground" -> currentLevel = LevelUnderground()
            "city" -> currentLevel = LevelCity()
            "mountains" -> currentLevel = LevelMountains()
        }
    }

    fun buildGameObjects(gameState: GameState){
        // Backgrounds 1, 2, 3(City, Underground, Mountain...)
        // p = Player
        // g = Grass tile
        // o = Objective
        // m = Movable platform
        // b = Brick tile
        // c = mine Cart
        // s = Stone pile
        // l = coaL
        // n = coNcrete
        // a = lAmpost
        // r = scoRched tile
        // w = snoW tile
        // t = stalacTite
        // i = stalagmIte
        // d = Dead tree
        // e = snowy trEe
        // x = Collectable
        // z = Fire
        // y = invisible death_invisible

        gameState.resetCoins()
        objects.clear()
        val levelToLoad = currentLevel?.tiles

        levelToLoad?.let {
            for(i in 0 until it.size){
                for(j in 0 until it[i].length){
                    val coords = PointF(j.toFloat(),i.toFloat())
                    when(it[i][j]){
                        '1' -> objects.add(factory.create(BackgroundCitySpec(),coords))
                        '2' -> objects.add(factory.create(BackgroundUndergroundSpec(), coords))
                        '3' -> objects.add(factory.create(BackgroundMountainSpec(), coords))
                        'p' -> {
                            objects.add(factory.create(PlayerSpec(),coords))
                            PLAYER_INDEX = objects.size -1
                        }
                        'g' -> objects.add(factory.create(GrassTileSpec(), coords))
                        'o' -> objects.add(factory.create(ObjectiveTileSpec(), coords))
                        'm' -> objects.add(factory.create(MoveablePlatformSpec(), coords))
                        'b' -> objects.add(factory.create(BrickTileSpec(), coords))
                        'c' -> objects.add(factory.create(CartTileSpec(), coords))
                        's' -> objects.add(factory.create(StonePileSpec(), coords))
                        'l' -> objects.add(factory.create(CoalTileSpec(), coords))
                        'n' -> objects.add(factory.create(ConcreteTileSpec(), coords))
                        'a' -> objects.add(factory.create(LamppostTileSpec(), coords))
                        'r' -> objects.add(factory.create(ScorchedTileSpec(), coords))
                        'w' -> objects.add(factory.create(SnowTileSpec(), coords))
                        't' -> objects.add(factory.create(StalactiteTileSpec(), coords))
                        'i' -> objects.add(factory.create(StalagmiteTileSpec(), coords))
                        'd' -> objects.add(factory.create(DeadTreeTileSpec(), coords))
                        'e' -> objects.add(factory.create(SnowyTreeTileSpec(), coords))
                        'x' -> {
                            objects.add(factory.create(CollectibleObjectSpec(), coords))
                            gameState.coinAddedToLevel()
                        }
                        'z' -> objects.add(factory.create(FireTileSpec(), coords))
                        'y' -> objects.add(factory.create(InvisibleDeathTenByTenSpec(), coords))
                        '.' -> airInLevel()
                        else -> Log.d("Unhandled item", "Row: $i, Column: $j")
                    }
                }
            }
        }

    }

    private fun airInLevel(){

    }

}