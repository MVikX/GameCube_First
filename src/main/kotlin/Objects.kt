package org.example

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle


class Objects (sceneWidth:Double, sceneHeight:Double,playerY: Double, playerHeight:Double) {
    val root = Pane()
    private val floor: Floor = Floor(sceneWidth, sceneHeight, playerY,playerHeight)
    private val random = kotlin.random.Random
    private var sceneWith = sceneWidth
    private var sceneHeight = sceneHeight
    private var playerHeight = playerHeight

    init {
        root.children.add(floor.getRectangle())

        root.sceneProperty().addListener { _, _, newScene ->
            if (newScene != null) {
                floor.updateFloor(newScene.width, newScene.height, playerHeight)
            }
        }
    }

    fun createBall(): Circle {
        val ball = Circle(random.nextDouble(5.0, 40.0)).apply {
            fill = Color.RED
            centerY = 0.0
            centerX = random.nextDouble((0.0 + (radius / 2)), (sceneWith - (radius / 2)))
        }
        return ball
    }
}