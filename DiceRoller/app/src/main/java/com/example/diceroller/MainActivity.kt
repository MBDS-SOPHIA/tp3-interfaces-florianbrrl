package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var rollButton: Button
    private lateinit var targetNumberEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        targetNumberEdit = findViewById(R.id.target_number)

        // Désactiver le bouton initialement
        rollButton.isEnabled = false

        // Ajouter un TextWatcher pour valider l'entrée
        targetNumberEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateTargetNumber(s.toString())
            }
        })

        rollButton.setOnClickListener { rollDice() }
    }

    private fun validateTargetNumber(input: String) {
        try {
            val number = input.toInt()
            // Valider que le nombre est entre 2 et 12 (somme possible avec 2 dés)
            rollButton.isEnabled = number in 2..12
        } catch (e: NumberFormatException) {
            rollButton.isEnabled = false
        }
    }

    private fun rollDice() {
        val targetNumber = targetNumberEdit.text.toString().toInt()
        val dice = Dice(6)
        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()
        val sum = diceRoll1 + diceRoll2

        // Mettre à jour l'affichage
        val resultTextView1: TextView = findViewById(R.id.result_text1)
        val resultTextView2: TextView = findViewById(R.id.result_text2)
        diceRoll1.toString().also { resultTextView1.text = it }
        diceRoll2.toString().also { resultTextView2.text = it }

        // Vérifier si l'utilisateur a gagné
        val message = if (sum == targetNumber) {
            "Félicitations ! Vous avez trouvé le nombre $targetNumber !"
        } else {
            "Dommage ! La somme est $sum"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}