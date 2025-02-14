package org.example

import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import org.example.GameScene.scene

class Player(sceneWidth: Double, sceneHeight: Double) {
    //корневой элемент игрока
    val root = Pane()

    //основные характеристики
    var lives = Constants.LIVES_START
    private var speed = 0.0

    //визуальные параметры
    val cubeSize = Rectangle(0.0, 0.0, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE).apply {
        fill = Color.BLACK
    }

    //управление
    private val activeKey = mutableListOf<KeyCode>()


    init {
        root.children.add(cubeSize)

        // координаты куба
        cubeSize.x = (scene.width / Constants.CENTER_OFFSET) - (cubeSize.width / Constants.CENTER_OFFSET)
        cubeSize.y = sceneHeight - (sceneHeight * Constants.PLAYER_START_POSITION_Y_RATIO)
    }


    fun cubeMove(scene: Scene) {
        scene.setOnKeyPressed { event ->
            if (event.code == KeyCode.A || event.code == KeyCode.D) {
                if (!activeKey.contains(event.code)) {
                    activeKey.add(event.code)
                }
            }
            when (event.code) {
                KeyCode.A -> speed = -Constants.PLAYER_SPEED //влево
                KeyCode.D -> speed = Constants.PLAYER_SPEED //вправо
                else -> {}
            }
        }
        scene.setOnKeyReleased { event ->
            activeKey.remove(event.code) //удаление клавиши
            if (activeKey.isEmpty()) {
                speed = Constants.PLAYER_STOP_SPEED //стоп куба
            }
        }
    }

    fun update(deltaTime: Double, sceneWidth: Double) {
        if (GameScene.gameManager.isGameOver) return

        cubeSize.x += speed * deltaTime
        if (cubeSize.x <= Constants.PLAYER_MIN_X) cubeSize.x = Constants.PLAYER_MIN_X
        if (cubeSize.x > scene.width - cubeSize.width) cubeSize.x = scene.width - cubeSize.width
    }
}
