package org.example

import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.stage.Stage

object GameScene {
    val root = Pane()
    val scene = Scene(root, 400.0, 700.0)

    var gameManager = GameManager()
    var isGameStart = false

    //меню
    private val startMenu = VBox()
    private val endMenu = VBox()

    //кнопки
    private val startButton = Button("Start")
    private val restartButton = Button("Restart")
    private val mainMenuButton = Button("Menu")

    val score = Score()
    lateinit var player: Player
    var floors = mutableListOf<Floor>()

    val livesText = Text("Lives: 3").apply {
        fill = Color.RED
        font = Font.font("Arial", FontWeight.BOLD, 30.0)
    }

    fun createScene(stage: Stage) {
        endMenu.alignment = Pos.CENTER
        root.children.addAll(startMenu, endMenu)
        stage.scene = scene
        stage.show()

        showStartMenu()
        //showEndMenu()
    }

    fun startGame() {
        isGameStart = true

        root.children.clear()

        startMenu.isVisible = false
        endMenu.isVisible = false

        gameManager = GameManager()
        score.scoreGame = 0
        score.updateScore()

        player = Player(scene.width, scene.height)
        val objects = Objects(scene.width, scene.height, player)

        root.children.addAll(objects.root)
        root.children.add(player.root)

        player.cubeMove(scene)

        root.children.add(score.textNode)
        score.textNode.y = 100.0
        score.textNode.x = scene.width / 2

        root.children.add(livesText)
        player.lives = 3
        livesText.text = "Lives: 3"

        updateLivesPosition()

        gameManager.timeGame(scene, player)
    }

    fun updateLivesPosition() {
        if (floors.isNotEmpty()) {
            val floor = floors.first()
            livesText.x = floor.root.layoutX + (floor.getRectangle().width / 2) - 40
            livesText.y = floor.root.layoutY + 30
        }
    }

    fun showEndMenu() {
        isGameStart = false
        gameManager.stopGame()

        if (!root.children.contains(endMenu)) {
            root.children.add(endMenu)
        }

        endMenu.isVisible = true
        endMenu.alignment = Pos.CENTER
        endMenu.prefWidth = scene.width
        endMenu.prefHeight = scene.height

        score.textGameOver.text = "LOSE. You score: ${score.scoreGame}"
        if (!endMenu.children.contains(score.textGameOver)) {
            endMenu.children.add(0, score.textGameOver)
        }

        restartButton.setOnAction {
            startGame()
        }

        restartButton.setPrefSize(100.0, 100.0)
        if (!endMenu.children.contains(restartButton)) {
            endMenu.children.add(restartButton)
        }

        mainMenuButton.setOnAction {
            showStartMenu()
        }

        mainMenuButton.setPrefSize(100.0, 100.0)
        endMenu.spacing = 10.0
        if (!endMenu.children.contains(mainMenuButton)) {
            endMenu.children.add(mainMenuButton)
        }
    }

    private fun showStartMenu() {
        isGameStart = false
        root.children.clear()

        startMenu.isVisible = true
        startMenu.alignment = Pos.CENTER
        startMenu.prefWidth = scene.width
        startMenu.prefHeight = scene.height

        startButton.setOnAction {
            startGame()
        }
        startButton.setPrefSize(100.0, 100.0)
        if (!startMenu.children.contains(startButton)) {
            startMenu.children.add(startButton)
        }
        root.children.add(startMenu)

        endMenu.isVisible = false
    }
}
