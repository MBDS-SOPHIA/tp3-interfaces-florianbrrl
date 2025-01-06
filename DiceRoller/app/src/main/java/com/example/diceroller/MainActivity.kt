package com.example.diceroller

import android.animation.ValueAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var targetNumberEdit: EditText
    private lateinit var resultTextView1: TextView
    private lateinit var resultTextView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        targetNumberEdit = findViewById(R.id.target_number)
        resultTextView1 = findViewById(R.id.result_text1)
        resultTextView2 = findViewById(R.id.result_text2)

        // Initialisation des valeurs
        resultTextView1.text = "0"
        resultTextView2.text = "0"

        targetNumberEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                try {
                    val text = s.toString()
                    if (text.isNotEmpty()) {
                        val number = text.toInt()
                        if (number in 2..12) {
                            rollDice(number)
                        } else {
                            Toast.makeText(this@MainActivity, R.string.invalid_number, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: NumberFormatException) {
                    // Ne rien faire si ce n'est pas un nombre valide
                }
            }
        })
    }

    private fun rollDice(targetNumber: Int) {
        val dice = Dice(6)
        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()
        val sum = diceRoll1 + diceRoll2

        // Mise à jour des résultats
        resultTextView1.text = diceRoll1.toString()
        resultTextView2.text = diceRoll2.toString()

        // Vérification de la victoire
        if (sum == targetNumber) {
            Toast.makeText(this, "Gagné! La somme est $sum", Toast.LENGTH_SHORT).show()
            animateWinning(resultTextView1, resultTextView2)
        }
    }

    private fun animateWinning(view1: View, view2: View) {
        ValueAnimator.ofFloat(0f, -50f, 0f).apply {
            duration = 800
            repeatCount = 2
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                val value = animator.animatedValue as Float
                view1.translationY = value
                view2.translationY = value
                view1.rotation = value * 0.5f
                view2.rotation = value * -0.5f
            }
            start()
        }
    }
}