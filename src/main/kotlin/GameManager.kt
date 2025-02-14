package org.example

import javafx.animation.AnimationTimer
import javafx.scene.Scene
import javafx.scene.shape.Circle
import org.example.GameScene.isGameStart

class GameManager {
    private var lastTime = System.nanoTime()
    private lateinit var loopGame: AnimationTimer
    private lateinit var objects: Objects
    private lateinit var collisionHandler: CollisionHandler
    private val intervalSpawn = 0.5
    private var timerSpawn = 0.0
    private val ballSpeed = 200.0
    val allBalls = mutableListOf<Circle>()
    var isGameOver = false

    fun timeGame(game: Scene, player: Player) {
        objects = Objects(game.width, game.height, player)
        collisionHandler = CollisionHandler(this)

        loopGame = object : AnimationTimer() {
            override fun handle(now: Long) {
                if (isGameOver) return

                val deltaTime = (now - lastTime) / 1_000_000_000.0
                lastTime = now

                // таймер спавна шаров
                if (isGameStart) {
                    timerSpawn += deltaTime
                }
                if (timerSpawn >= intervalSpawn) {
                    val newBall = objects.createBall()
                    GameScene.root.children.add(newBall)
                    allBalls.add(newBall)
                    timerSpawn = 0.0
                }

                updateBalls(deltaTime)

                collisionHandler.checkCollision()

                player.update(deltaTime, game.width)
            }
        }
        loopGame.start()
    }

    private fun updateBalls(deltaTime: Double) {
        if (isGameOver) return

        for (ball in allBalls) {
            ball.centerY += ballSpeed * deltaTime
        }
    }


    fun stopGame() {
        isGameOver = true
        if (::loopGame.isInitialized) {
            loopGame.stop()
        }
    }
}
