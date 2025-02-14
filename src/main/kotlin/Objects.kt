package org.example

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle


class Objects (sceneWidth:Double, sceneHeight:Double, player:Player) {
    val root = Pane()
    private val floors = mutableListOf<Floor>()
    private val random = kotlin.random.Random
    private var sceneWith = sceneWidth
    private var sceneHeight = sceneHeight


    init {
        addFloorPlayer(player)
    }

    fun addFloorPlayer(player: Player) {
        val floorX = 0.0
        val floorY = player.cubeSize.y + player.cubeSize.height
        val floorWidth = sceneWith
        val floorHeight = sceneHeight

        val floor = Floor(floorX, floorY, floorHeight, floorWidth)
        floors.add(floor)
        root.children.add(floor.getRectangle())
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