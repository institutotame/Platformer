package com.atinem.platformer

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import java.io.IOException

object SoundEngine {

    private lateinit var soundPool: SoundPool
    private var jumpID: Int = -1
    private var reachObjectiveID: Int = -1
    private var coinPickupID: Int = -1
    private var playerBurnID: Int = -1



    operator fun invoke(context: Context): SoundEngine{
        soundPool = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioAttributes).build()
        }else{
            SoundPool(5,AudioManager.STREAM_MUSIC, 0)
        }
        try{
            val assetManager: AssetManager = context.assets
            var descriptor: AssetFileDescriptor = assetManager.openFd("jump.ogg")
            jumpID = soundPool.load(descriptor,0)

            descriptor = assetManager.openFd("reach_objective.ogg")
            reachObjectiveID = soundPool.load(descriptor,0)

            descriptor = assetManager.openFd("coin_pickup.ogg")
            coinPickupID = soundPool.load(descriptor,0)

            descriptor = assetManager.openFd("player_burn.ogg")
            playerBurnID = soundPool.load(descriptor,0)
        }catch (e: IOException){
            e.printStackTrace()
        }
        return this
    }

    fun playJump() = soundPool.play(jumpID, 1f,1f,0,0,1f)

    fun playReachObjective() = soundPool.play(reachObjectiveID, 1f,1f,0,0,1f)

    fun playCoinPickup() = soundPool.play(coinPickupID, 1f,1f,0,0,1f)

    fun playPlayerBurn() = soundPool.play(playerBurnID, 1f,1f,0,0,1f)
}