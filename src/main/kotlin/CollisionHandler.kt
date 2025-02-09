package org.example

const val floorY = 575  // Временное решение


class CollisionHandler(private val allBalls: GameManager) {

    fun checkCollision() {
        val iterator = allBalls.allBalls.iterator()
        while (iterator.hasNext()) {
            val ball = iterator.next()

            if (ball.centerY >= floorY - ball.radius) {
                iterator.remove()
                GameScene.root.children.remove(ball)
            }
        }
    }
}
