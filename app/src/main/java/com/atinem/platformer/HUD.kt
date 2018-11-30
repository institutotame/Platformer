package com.atinem.platformer

import android.content.Context
import android.graphics.*

class HUD(context: Context, size: Point) {
    val screenHeight: Int
    val screenWidth: Int

    val textFormatting: Int

    val menuBitmap: Bitmap

    val controls: List<Rect>

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

    private fun prepareControls(): List<Rect>{
        val buttonWidth = screenWidth / 14
        val buttonHeight = screenHeight / 12
        val buttonPadding = screenWidth / 90

        val left = Rect(buttonPadding,
            screenHeight - buttonHeight - buttonPadding,
            buttonWidth - buttonPadding,
            screenHeight - buttonPadding
        )

        val right = Rect(
            buttonPadding * 2 + buttonWidth,
            screenHeight - buttonHeight - buttonPadding,
            buttonPadding * 2 + buttonWidth *2,
            screenHeight - buttonPadding
        )

        val jump = Rect(
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
            canvas.drawRect(0f,0f,screenWidth.toFloat(), textFormatting * 4f, paint)

            //Draw the level names
            paint.color = Color.argb(255,255,255,255)
            paint.textSize = textFormatting.toFloat()
            canvas.drawText("Underground", textFormatting.toFloat(), textFormatting * 2f, paint)
            canvas.drawText("Mountains", screenWidth * ONE_THIRD + textFormatting, textFormatting * 2f, paint)
            canvas.drawText("City", screenWidth * TWO_THIRDS + textFormatting, textFormatting * 2f, paint)

            // Draw the fastest times
            paint.textSize = textFormatting / 1.8f
            canvas.drawText("BEST: ${gameState.fastestUnderground} seconds",
                textFormatting.toFloat(),
                textFormatting * 3f,
                paint
            )
            canvas.drawText("BEST: ${gameState.fastestMountains} seconds",
                screenWidth * ONE_THIRD + textFormatting,
                textFormatting * 3f,
                paint
            )
            canvas.drawText("BEST: ${gameState.fastestCity} seconds",
                screenWidth * TWO_THIRDS + textFormatting,
                textFormatting * 3f,
                paint
            )

            // Draw a rectanble to highlight the large text
            paint.color = Color.argb(100,26,128,182)
            canvas.drawRect(0f,screenHeight - textFormatting * 2f, screenWidth.toFloat(), screenHeight.toFloat(), paint)
            paint.color =  Color.argb(255, 255,255,255)
            paint.textSize = textFormatting * 1.5f
            canvas.drawText("DOUBLE TAP A LEVEL TO PLAY",
                ONE_THIRD + textFormatting * 2,
                screenHeight - textFormatting / 2f,
                paint
            )
        }else{
            paint.color = Color.argb(100,0,0,0)
            canvas.drawRect(0f,0f,screenWidth.toFloat(),textFormatting.toFloat(),paint)

            // Draw HUD text
            paint.textSize = textFormatting / 1.5f
            paint.color = Color.argb(255,255,255,255)
            canvas.drawText("Time ${gameState.getCurrentTime()} + ${gameState.getCoinsRemaining() * 10}",
                textFormatting / 4f,
                textFormatting / 1.5f,
                paint
            )

            drawControls(canvas,paint)
        }
    }

    fun drawControls(canvas: Canvas, paint: Paint){
        paint.color = Color.argb(100,255,255,255)

        for(rect in controls){
            canvas.drawRect(rect.left.toFloat(), rect.top.toFloat(), rect.right.toFloat(),rect.bottom.toFloat(), paint)
        }

        paint.color = Color.argb(255,255,255,255)
    }
}