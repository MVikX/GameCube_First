package org.example

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Floor (
    private val sceneWidth: Double,
    private val sceneHeight: Double,
    private val playerY: Double,
    private val playerHeight: Double
) {
    private val rectangle: Rectangle

    init {
        rectangle = Rectangle(0.0, calculateY(), sceneWidth, calculateHeight()).apply {
            fill = Color.GREY
        }
    }

    fun getRectangle(): Rectangle {
        return rectangle
    }

    private fun calculateY(): Double {
        return playerY + playerHeight
    }

    private fun calculateHeight(): Double {
        return sceneHeight - calculateY()
    }

    fun updateFloor(sceneWidth:Double, sceneHeight:Double, playerHeight:Double) {
        rectangle.width = sceneWidth
        rectangle.height = sceneHeight - (sceneHeight - playerHeight)
        rectangle.y = sceneHeight - playerHeight
    }

}