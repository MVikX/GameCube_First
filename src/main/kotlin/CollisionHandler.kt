package org.example

class CollisionHandler(private val allBalls: GameManager) {

    fun checkCollision() {
        val iterator = allBalls.allBalls.iterator()
        while (iterator.hasNext()) {
            val ball = iterator.next()

            // Берем первый пол из списка floors
            val firstFloor = GameScene.floors.firstOrNull()
            if (firstFloor != null) {
                val floorY = firstFloor.getRectangle().y

                if (ball.centerY >= floorY - ball.radius) {
                    iterator.remove()
                    GameScene.root.children.remove(ball)

                    GameScene.score.scoreGame++
                    GameScene.score.updateScore()
                }
            }

            if (ball.boundsInParent.intersects(GameScene.player.cubeSize.boundsInParent)) {
                GameScene.player.lives--
                GameScene.score.updateLives(GameScene.player.lives)

                if (GameScene.player.lives <= 0) {
                    GameScene.score.scoreGame = 0
                    GameScene.score.updateScore()
                    GameScene.showEndMenu()
                }

                iterator.remove()
                GameScene.root.children.remove(ball)
            }
        }
    }
}
