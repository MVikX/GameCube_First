package org.example

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.layout.Pane

class Floor(
    playerY: Double,
    playerHeight: Double,
    sceneWidth: Double,
    sceneHeight: Double
) {
    private val rectangle: Rectangle = Rectangle(
        0.0,
        playerY + playerHeight,
        sceneWidth,
        sceneHeight - (playerY + playerHeight)
    ).apply {
        fill = Color.GREY
    }

    val root = Pane().apply {
        children.add(rectangle)
    }

    fun getRectangle(): Rectangle {
        return rectangle
    }

    fun updateFloor(playerY: Double, playerHeight: Double, sceneWidth: Double, sceneHeight: Double) {
        rectangle.y = playerY + playerHeight
        rectangle.height = sceneHeight - (playerY + playerHeight)
        rectangle.width = sceneWidth
    }
}
