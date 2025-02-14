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
    //основные элементы сцены
    val root = Pane()
    val scene = Scene(root, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT)

    //управление игрой
    var gameManager = GameManager()
    var isGameStart = false

    //меню и UI
    private val startMenu = VBox()
    private val endMenu = VBox()
    private val startButton = Button("Start")
    private val restartButton = Button("Restart")
    private val mainMenuButton = Button("Menu")

    //игровые элементы
    val score = Score()
    lateinit var player: Player
    val floors = mutableListOf<Floor>()

    //UI элементы
    val livesText = Text("Lives: 3").apply {
        fill = Color.RED
        font = Font.font(Constants.FONT_FAMILY, FontWeight.BOLD, Constants.FONT_SIZE)
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

        root.children.clear() //очистка сцены

        startMenu.isVisible = false
        endMenu.isVisible = false

        gameManager = GameManager()
        score.scoreGame = 0
        score.updateScore()

        player = Player(scene.width, scene.height)
        val objects = Objects(scene.width, scene.height, player)

        root.children.addAll(objects.root)
        root.children.add(player.root)

        player.cubeMove(scene) //подключение управления

        //отоброжение счетчика
        root.children.add(score.textNode)
        score.textNode.y = Constants.SCORE_TEXT_Y
        score.textNode.x = scene.width / Constants.CENTER_OFFSET

        //отоброжение жизней
        root.children.add(livesText)
        player.lives = Constants.LIVES_START
        livesText.text = "Lives: ${Constants.LIVES_START.toByte()}"

        updateLivesPosition() //обновление позиции жизни

        gameManager.timeGame(scene, player) //запуск игрового цикла
    }

    fun updateLivesPosition() {
        if (floors.isNotEmpty()) {
            val floor = floors.first()
            livesText.x = floor.root.layoutX + (floor.getRectangle().width / Constants.CENTER_OFFSET) - Constants.LIVES_TEXT_OFFSET_X
            livesText.y = floor.root.layoutY + Constants.LIVES_TEXT_OFFSET_Y
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
            endMenu.children.add(Constants.MENU_FIRST_POSITION, score.textGameOver)
        }

        restartButton.setOnAction {
            startGame()
        }

        restartButton.setPrefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)
        if (!endMenu.children.contains(restartButton)) {
            endMenu.children.add(restartButton)
        }

        mainMenuButton.setOnAction {
            showStartMenu()
        }

        mainMenuButton.setPrefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)
        endMenu.spacing = Constants.MENU_SPACING
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
        startButton.setPrefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT)
        if (!startMenu.children.contains(startButton)) {
            startMenu.children.add(startButton)
        }
        root.children.add(startMenu)

        endMenu.isVisible = false
    }
}
