package org.example

import javafx.application.Application
import javafx.stage.Stage

class Game : Application() {

    override fun start(stage: Stage) {

        GameScene.createScene(stage)

    }
}
