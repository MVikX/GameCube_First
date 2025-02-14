package org.example

import javafx.animation.AnimationTimer
import javafx.scene.Scene
import javafx.scene.shape.Circle
import org.example.GameScene.isGameStart


class GameManager {
    // статусы игры
    var isGameOver = false
    private var lastTime = System.nanoTime()
    private var timerSpawn = 0.0

    // объекты игры
    lateinit var loopGame: AnimationTimer
    lateinit var objects: Objects
    lateinit var collisionHandler: CollisionHandler

    // список шаров
    val allBalls = mutableListOf<Circle>()


    fun timeGame(game: Scene, player: Player) {
        objects = Objects(game.width, game.height, player)
        collisionHandler = CollisionHandler(this)

        loopGame = object : AnimationTimer() {
            override fun handle(now: Long) {
                if (isGameOver) return

                val deltaTime = (now - lastTime) / Constants.NANO_TO_SECONDS
                lastTime = now

                // таймер спавна шаров
                if (isGameStart) {
                    timerSpawn += deltaTime
                }

                //спавн нового шара
                if (timerSpawn >= Constants.BALL_SPAWN_INTERVAL) {
                    val newBall = objects.createBall()
                    GameScene.root.children.add(newBall)
                    allBalls.add(newBall)
                    timerSpawn = 0.0
                }

                updateBalls(deltaTime) // обновление позиции шаров
                collisionHandler.checkCollision() // проверка столкновений
                player.update(deltaTime, game.width) // движение игрока
            }
        }
        loopGame.start()
    }

    private fun updateBalls(deltaTime: Double) {
        if (isGameOver) return

        for (ball in allBalls) {
            ball.centerY += Constants.BALL_SPEED * deltaTime
        }
    }


    fun stopGame() {
        isGameOver = true
        if (::loopGame.isInitialized) {
            loopGame.stop()
        }
    }
}
