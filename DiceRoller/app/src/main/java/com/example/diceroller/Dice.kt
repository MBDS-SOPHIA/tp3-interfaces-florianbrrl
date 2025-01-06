package com.example.diceroller

/**
 * Dice with a fixed number of sides.
 *
 * @property numSides The number of sides on the dice.
 */
class Dice(private val numSides: Int) {
    /**
     * Do a random dice roll and return the result.
     *
     * @return A random value between 1 and numSides.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}