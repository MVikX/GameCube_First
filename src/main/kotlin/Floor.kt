package org.example

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Floor (
    x: Double,
    y: Double,
    width: Double,
    height: Double
) {

    private val rectangle:Rectangle = Rectangle(x, y, width, height).apply {
        fill = Color.GREY
    }

    fun getRectangle(): Rectangle {
        return rectangle
    }

    var x: Double
    get() = rectangle.x
    set(value) {
        rectangle.x = value
    }

    var y: Double
    get() = rectangle.y
    set(value) {
        rectangle.y = value
    }

    var width: Double
    get() = rectangle.width
    set(value) {
        rectangle.width = value
    }

    var height: Double
    get() = rectangle.height
    set(value) {
        rectangle.height = value
    }

}