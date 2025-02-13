package org.example

import javafx.application.Application
import javafx.stage.Stage


class Game : Application() {
    override fun start(stage: Stage) {
        val gameManager = GameManager()


        GameScene.createScene(stage)
        GameScene.startGame()
        gameManager.timeGame(stage.scene, player = GameScene.player)
    }
}