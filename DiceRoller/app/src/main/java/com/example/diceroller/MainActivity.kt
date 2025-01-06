package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

/**
 * This activity allows the user to roll two dice and view the result
 * on the screen. If both dice show the same number, the user wins!
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Roll both dice and update the screen with the results.
     * Show a winning message if both dice show the same number.
     */
    private fun rollDice() {
        // Create two dice objects and roll them
        val dice = Dice(6)
        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()

        // Update the screen with both dice rolls
        val resultTextView1: TextView = findViewById(R.id.result_text1)
        val resultTextView2: TextView = findViewById(R.id.result_text2)
        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()

        // Check if the user won (both dice show same number)
        if (diceRoll1 == diceRoll2) {
            Toast.makeText(
                this,
                "Félicitations ! Vous avez gagné !",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}