package org.example

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle


class Objects (sceneWidth:Double, sceneHeight:Double, playerHeight:Double) {
    val root = Pane()
    val floor = Rectangle()
    private val random = kotlin.random.Random
    private var sceneWith = sceneWidth
    private var sceneHeight = sceneHeight
    private var playerHeight = playerHeight



    fun floorD () {
        floor.apply {
            x = 0.0
            y = sceneHeight - (sceneHeight / 4) + playerHeight
            width = sceneWith
            height = sceneHeight
            fill = Color.GREY
        }

        root.children.add(floor)

        //Слежка за обновлением сцены
        root.sceneProperty().addListener { _, _, newScene ->
            if (newScene != null) {
                sceneWith = newScene.width
                sceneHeight = newScene.height
                floor.width = sceneWith
                floor.y = sceneHeight - (sceneHeight / 4) + playerHeight
            }
        }
    }

    fun createBall () : Circle {
        val ball = Circle(random.nextDouble(5.0, 40.0)).apply {
            fill = Color.RED
            centerY = 0.0
            centerX = random.nextDouble((0.0 + (radius / 2)), (sceneWith - (radius / 2)) )
        }
        return ball
    }


    init {
        floorD()
        if (!root.children.contains(floor)) {
            root.children.add(floor)
        }


        root.sceneProperty().addListener { _, _, newScene ->
            if (newScene != null ) {
                sceneWith = newScene.width
            }
        }
    }
}