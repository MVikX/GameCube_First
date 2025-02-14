package org.example

import javafx.application.Platform

class CollisionHandler(private val gameManager: GameManager) {

    fun checkCollision() {
        //перебор шаров
        val iterator = gameManager.allBalls.iterator()

        while (iterator.hasNext()) {
            val ball = iterator.next()

            //проверка столкновений
            val firstFloor = GameScene.floors.firstOrNull()
            firstFloor?.let {
                val floorY = it.getRectangle().y

                if (ball.centerY >= floorY - ball.radius) {
                    iterator.remove()
                    GameScene.root.children.remove(ball)

                    //увелечение счета
                    GameScene.score.scoreGame++
                    GameScene.score.updateScore()
                }
            }

            //проверка столкновения с игроком
            val player = GameScene.player
            if (ball.boundsInParent.intersects(player.cubeSize.boundsInParent)) {
                player.lives--
                GameScene.score.updateLives(player.lives)

                //конец игры
                if (player.lives == 0) {
                    gameManager.stopGame()
                    Platform.runLater {
                        GameScene.score.textGameOver.text = "LOSE. You score: ${GameScene.score.scoreGame}"
                        GameScene.showEndMenu()
                    }
                }

                //удаление мячей
                iterator.remove()
                GameScene.root.children.remove(ball)
            }
        }
    }
}
