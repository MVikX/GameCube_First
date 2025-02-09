package org.example

import javafx.animation.AnimationTimer
import javafx.scene.Scene
import javafx.scene.shape.Circle
import org.example.GameScene.isGameStart

class GameManager {
    private var lastTime = System.nanoTime()
    private lateinit var loopGame: AnimationTimer
    private val objects = Objects()
    private val intervalSpawn = 0.5
    private var timerSpawn = 0.0
    private val ballSpeed = 200.0
    val allBalls = mutableListOf<Circle>()
    val collisionHandler = CollisionHandler(this)

    fun timeGame(game: Scene, player: Player) {
        loopGame = object : AnimationTimer() {
            override fun handle(now: Long) {
                val deltaTime = (now - lastTime) / 1_000_000_000.0
                lastTime = now

                // Таймер спавна шаров
                if (isGameStart) {
                    timerSpawn += deltaTime
                }
                if (timerSpawn >= intervalSpawn) {
                    val newBall = objects.createBall()
                    GameScene.root.children.add(newBall)
                    allBalls.add(newBall)
                    timerSpawn = 0.0
                }

                // Обновление движения шаров
                updateBalls(deltaTime)

                // Проверка столкновений с полом
                collisionHandler.checkCollision()

                player.update(deltaTime)
            }
        }
        loopGame.start()
    }

    // Обновление падения шаров
    private fun updateBalls(deltaTime: Double) {
        for (ball in allBalls) {
            ball.centerY += ballSpeed * deltaTime
        }
    }
}
