package com.atinem.platformer

import android.content.Context
import android.graphics.Point
import android.graphics.PointF
import android.provider.Contacts
import android.provider.Settings
import android.view.MotionEvent
import android.view.SurfaceView
import com.atinem.platformer.components.EngineController
import com.atinem.platformer.components.GameEngineBroadcaster
import com.atinem.platformer.components.InputObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList

class GameEngine(context: Context, size: Point): SurfaceView(context), GameEngineBroadcaster, EngineController {
    private var job: Job? = null
    private var fps: Long = 0

    val inputObservers: CopyOnWriteArrayList<InputObserver> = CopyOnWriteArrayList()

    private val gameState: GameState = GameState(this,context)
    val uiController: UIController = UIController(this,size)



    val hud: HUD = HUD(context,size)
    val levelManager: LevelManager
    val physicsEngine: PhysicsEngine = PhysicsEngine()
    val renderer: Renderer = Renderer(this.holder,size)

    init {
        val bitmapStore = BitmapStore(context)
        val soundEngine = SoundEngine(context)
        levelManager = LevelManager(context,this,renderer.getPixelsPerMetre().toInt())
    }

    override fun addObserver(observer: InputObserver) {
        inputObservers.add(observer)
    }

    override fun startNewLevel() {
        // Clear the bitmap store
        BitmapStore.clearStore()
        // Clear all the observers and add the UI observer back
        inputObservers.clear()
        uiController.addObserver(this)
        levelManager.setCurrentLevel(gameState.currentLevel)
        levelManager.buildGameObjects(gameState)
    }

    fun launchCoroutine(){
        job = GlobalScope.launch {
            while(gameState.threadRunning){
                val frameStartTime = System.currentTimeMillis()
                if(!gameState.paused){
                    physicsEngine.update(fps,levelManager.objects, gameState)
                }
                renderer.draw(levelManager.objects, gameState, hud)

                val timeThisFrame = System.currentTimeMillis() - frameStartTime

                if(timeThisFrame >= 1){
                    val MILLIS_IN_SECOND = 1000
                    fps = MILLIS_IN_SECOND / timeThisFrame
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            for(inputObserver in inputObservers){
                inputObserver.handleInput(it, gameState,hud.controls)
            }
            return true
        }
        return false
    }

    fun stopJob(){
        gameState.stopEverything()
        gameState.stopThread()
        job?.cancel()
    }

    fun startJob(){
        gameState.startThread()
        launchCoroutine()
    }
}