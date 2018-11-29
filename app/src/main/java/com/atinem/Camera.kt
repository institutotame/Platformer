package com.atinem

import android.graphics.PointF
import android.graphics.RectF

class Camera(screenXResolution: Float, screenYResolution: Float) {
    val currentCameraWorldCentre: PointF
    val convertedRect: RectF
    val pixelsPerMetre: Float
    val screenCentreX: Float
    val screenCentreY: Float

    init {
        screenCentreX = screenXResolution / 2
        screenCentreY = screenYResolution / 2

        val pixelsPerMetreToResolutionRatio: Int = 48

        pixelsPerMetre = screenXResolution * pixelsPerMetreToResolutionRatio

        convertedRect = RectF()
        currentCameraWorldCentre = PointF()

    }

    fun setWorldCentre(worldCentre: PointF){
        currentCameraWorldCentre.x = worldCentre.x
        currentCameraWorldCentre.y = worldCentre.y
    }

    fun worldToScreen(objectX: Float, objectY: Float, objectWidth: Float, objectHeight: Float): RectF{
        val left = (screenCentreX - ((currentCameraWorldCentre.x - objectX) * pixelsPerMetre))

        val top = (screenCentreY - ((currentCameraWorldCentre.y - objectY) * pixelsPerMetre))

        val right = (left + (objectWidth * pixelsPerMetre))

        val bottom = (top + (objectHeight * pixelsPerMetre))

        convertedRect.set(left,top,right,bottom)

        return convertedRect
    }
}