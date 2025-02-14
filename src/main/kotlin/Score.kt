package org.example

import javafx.application.Platform
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text

class Score {
    //счёт игры
    var scoreGame = 0

    //UI элементы
    val textNode = Text("$scoreGame").apply {
        fill = Color.BLACK
        font = Font.font(Constants.SCORE_FONT_SIZE)
    }

    val textGameOver = Text("LOSE. You score: $scoreGame").apply {
        fill = Color.BLACK
        font = Font.font(Constants.GAME_OVER_FONT_SIZE)
    }


    fun updateScore() {
        textNode.text = "$scoreGame"
    }


    fun updateLives(lives: Int) {
        Platform.runLater {
            GameScene.livesText.text = "Lives: ${lives.toByte()}"
            GameScene.updateLivesPosition()

            GameScene.livesText.parent?.requestLayout()
        }
    }
}
