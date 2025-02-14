package org.example

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle


class Objects(sceneWidth: Double, sceneHeight: Double, player: Player) {
    val root = Pane()
    private val floors = mutableListOf<Floor>()
    private val random = kotlin.random.Random
    private var sceneWith = sceneWidth
    private var sceneHeight = sceneHeight

    init {
        createFloors(player)
    }

    private fun createFloors(player: Player) {
        val mainFloor = Floor(
            playerY = player.cubeSize.y,
            playerHeight = player.cubeSize.height,
            sceneWidth = sceneWith,
            sceneHeight = sceneHeight
        )
        floors.add(mainFloor)
        root.children.add(mainFloor.root)

        GameScene.floors.add(mainFloor)
    }

    fun createBall(): Circle {
        val radius = random.nextDouble(Constants.BALL_MIN_RADIUS, Constants.BALL_MAX_RADIUS)

        val minX = radius
        val maxX = sceneWith - radius

        val validX = random.nextDouble(
            minOf(minX, maxX),
            maxOf(minX, maxX)
        )

        val ball = Circle(radius).apply {
            fill = Color.RED
            centerY = Constants.BALL_START_Y
            centerX = validX
        }

        return ball
    }

    fun updateFloor(lives: Int) {
        GameScene.score.updateLives(lives)
    }
}
