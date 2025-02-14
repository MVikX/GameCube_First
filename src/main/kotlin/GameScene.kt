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
    val gameManager = GameManager()
    var isGameStart = false
    private val startMenu = VBox()
    private val endMenu = VBox()
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
        root.children.addAll(startMenu, endMenu)
        stage.scene = scene
        stage.show()
    }

    fun startGame() {
        isGameStart = true
        startMenu.isVisible = false
        endMenu.isVisible = false
        root.children.clear()

        player = Player(scene.width, scene.height)
        val objects = Objects(scene.width, scene.height, player)

        root.children.addAll(objects.root)
        root.children.add(player.root)

        player.cubeMove(scene)

        root.children.add(score.textNode)
        score.textNode.y = 100.0
        score.textNode.x = scene.width / 2

        root.children.add(livesText)

        updateLivesPosition()
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
        endMenu.isVisible = true
        endMenu.alignment = Pos.CENTER
        endMenu.prefWidth = scene.width
        endMenu.prefHeight = scene.height

        restartButton.setOnAction { startGame() }
        restartButton.setPrefSize(100.0, 100.0)
        if (!endMenu.children.contains(restartButton)) {
            endMenu.children.add(restartButton)
        }

        mainMenuButton.setOnAction { showStartMenu() }
        mainMenuButton.setPrefSize(100.0, 100.0)
        endMenu.spacing = 10.0
        if (!endMenu.children.contains(mainMenuButton)) {
            endMenu.children.add(mainMenuButton)
        }
    }

    private fun showStartMenu() {
        isGameStart = false
        startMenu.isVisible = true
        startMenu.alignment = Pos.CENTER
        startMenu.prefWidth = scene.width
        startMenu.prefHeight = scene.height

        startButton.setOnAction { startGame() }
        startButton.setPrefSize(100.0, 100.0)
        if (!startMenu.children.contains(startButton)) {
            startMenu.children.add(startButton)
        }

        endMenu.isVisible = false
    }
}
