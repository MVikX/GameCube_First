package org.example

object Constants {

    //ПАРАМЕТРЫ ИГРОКА (PLAYER)

    const val PLAYER_SIZE = 50.0 // размер кубика игрока (ширина и высота)
    const val PLAYER_SPEED = 200.0 // скорость передвижения игрока
    const val PLAYER_STOP_SPEED = 0.0 // остановка игрока при отпускании клавиши

    const val PLAYER_START_POSITION_Y_RATIO = 0.25 // позиция игрока (25% от высоты экрана)
    const val PLAYER_MIN_X = 0.0 // минимальная граница движения по X (край слева)

    //ПАРАМЕТРЫ ШАРОВ (BALL)

    const val BALL_MIN_RADIUS = 5.0 // минимальный радиус шара
    const val BALL_MAX_RADIUS = 35.0 // максимальный радиус шара
    const val BALL_SPAWN_INTERVAL = 0.3 // сремя между спавнами шаров (секунды)
    const val BALL_SPEED = 350.0 // скорость падения шаров
    const val BALL_START_Y = 0.0 // начальная позиция шара по Y (шары падают сверху)

    //ПАРАМЕТРЫ СЦЕНЫ (SCENE)

    const val SCENE_WIDTH = 400.0 // ширина окна игры
    const val SCENE_HEIGHT = 700.0 // высота окна игры

    //КОЛИЧЕСТВО ЖИЗНЕЙ (LIVES)

    const val LIVES_START = 3 // количество жизней в начале игры

    // Позиция отображения текста жизней
    const val LIVES_TEXT_OFFSET_X = 40.0 // отступ текста жизней по X
    const val LIVES_TEXT_OFFSET_Y = 30.0 // отступ текста жизней по Y

    //ПОЗИЦИЯ СЧЁТЧИКА (SCORE)

    const val SCORE_TEXT_Y = 100.0 // позиция счётчика по Y
    const val SCORE_FONT_SIZE = 50.0 // размер шрифта для счётчика очков

    //РАЗМЕРЫ КНОПОК (BUTTONS)

    const val BUTTON_WIDTH = 100.0 // щирина кнопок меню
    const val BUTTON_HEIGHT = 100.0 // высота кнопок меню

    //ВРЕМЯ И КОНВЕРСИЯ (TIME)

    const val NANO_TO_SECONDS = 1_000_000_000.0 // конвертация наносекунд в секунды

    //ЦЕНТРОВАНИЕ ЭЛЕМЕНТОВ (CENTERING)

    const val CENTER_OFFSET = 2.0 // смещение для центрирования элементов

    //НАСТРОЙКИ ТЕКСТА (TEXT)

    const val FONT_SIZE = 30.0 // размер шрифта
    const val FONT_FAMILY = "Arial" // семейство шрифтов
    const val GAME_OVER_FONT_SIZE = 40.0 // размер шрифта для экрана Game Over

    //НАСТРОЙКИ МЕНЮ (MENU)

    const val MENU_FIRST_POSITION = 0 // индекс первого элемента в меню
    const val MENU_SPACING = 10.0 // расстояние между элементами меню
}
