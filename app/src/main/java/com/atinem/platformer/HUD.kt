package com.atinem.platformer

import android.content.Context
import android.graphics.*

class HUD(context: Context, size: PointF) {
    val screenHeight: Float
    val screenWidth: Float

    val textFormatting: Float

    val menuBitmap: Bitmap

    val controls: List<RectF>

    companion object {
        const val ONE_THIRD = .33f
        const val TWO_THIRDS = .66f
    }

    init {
        screenHeight = size.y
        screenWidth = size.x

        textFormatting = size.x / 25

        controls = prepareControls()

        val bitmapBase = BitmapFactory.decodeResource(context.resources, R.drawable.menu)

        menuBitmap = Bitmap.createScaledBitmap(bitmapBase, size.x.toInt(), size.y.toInt(), false)
    }

    private fun prepareControls(): List<RectF>{
        val buttonWidth = screenWidth / 14
        val buttonHeight = screenHeight / 12
        val buttonPadding = screenWidth / 90

        val left = RectF(buttonPadding,
            screenHeight - buttonHeight - buttonPadding,
            buttonWidth - buttonPadding,
            screenHeight - buttonPadding
        )

        val right = RectF(
            buttonPadding * 2 + buttonWidth,
            screenHeight - buttonHeight - buttonPadding,
            buttonPadding * 2 + buttonWidth *2,
            screenHeight - buttonPadding
        )

        val jump = RectF(
            screenWidth - buttonPadding - buttonWidth,
            screenHeight - buttonHeight - buttonPadding,
            buttonWidth - buttonPadding,
            buttonHeight - buttonPadding
        )
        return listOf(left, right, jump)
    }

    fun draw(canvas: Canvas, paint: Paint, gameState: GameState){
        if(gameState.gameOver){
            canvas.drawBitmap(menuBitmap,0f,0f,paint)
            paint.color = Color.argb(100,26,128,182)
            canvas.drawRect(0f,0f,screenWidth, textFormatting * 4, paint)

            //Draw the level names
            paint.color = Color.argb(255,255,255,255)
            paint.textSize = textFormatting
            canvas.drawText("Underground", textFormatting, textFormatting * 2, paint)
            canvas.drawText("Mountains", screenWidth * ONE_THIRD + textFormatting, textFormatting * 2, paint)
            canvas.drawText("City", screenWidth * TWO_THIRDS + textFormatting, textFormatting * 2, paint)

            // Draw the fastest times
            paint.textSize = textFormatting / 1.8f
            canvas.drawText("BEST: ${gameState.fastestUnderground} seconds",
                textFormatting,
                textFormatting * 3,
                paint
            )
            canvas.drawText("BEST: ${gameState.fastestMountains} seconds",
                screenWidth * ONE_THIRD + textFormatting,
                textFormatting * 3,
                paint
            )
            canvas.drawText("BEST: ${gameState.fastestCity} seconds",
                screenWidth * TWO_THIRDS + textFormatting,
                textFormatting * 3,
                paint
            )

            // Draw a rectanble to highlight the large text
            paint.color = Color.argb(100,26,128,182)
            canvas.drawRect(0f,screenHeight - textFormatting * 2, screenWidth, screenHeight, paint)
            paint.color =  Color.argb(255, 255,255,255)
            paint.textSize = textFormatting * 1.5f
            canvas.drawText("DOUBLE TAP A LEVEL TO PLAY",
                ONE_THIRD + textFormatting * 2,
                screenHeight - textFormatting / 2,
                paint
            )
        }else{
            paint.color = Color.argb(100,0,0,0)
            canvas.drawRect(0f,0f,screenWidth,textFormatting,paint)

            // Draw HUD text
            paint.textSize = textFormatting / 1.5f
            paint.color = Color.argb(255,255,255,255)
            canvas.drawText("Time ${gameState.getCurrentTime()} + ${gameState.getCoinsRemaining() * 10}",
                textFormatting / 4,
                textFormatting / 1.5f,
                paint
            )

            drawControls(canvas,paint)
        }
    }

    fun drawControls(canvas: Canvas, paint: Paint){
        paint.color = Color.argb(100,255,255,255)

        for(rect in controls){
            canvas.drawRect(rect.left, rect.top, rect.right,rect.bottom, paint)
        }

        paint.color = Color.argb(255,255,255,255)
    }
}