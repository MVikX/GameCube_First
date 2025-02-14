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
        playerY + playerHeight,  // Пол начинается от низа куба
        sceneWidth,
        sceneHeight - (playerY + playerHeight)  // Высота до низа экрана
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
        rectangle.y = playerY + playerHeight  // Обновляем позицию пола
        rectangle.height = sceneHeight - (playerY + playerHeight)  // Высота до конца экрана
        rectangle.width = sceneWidth  // Ширина на всю сцену
    }
}
