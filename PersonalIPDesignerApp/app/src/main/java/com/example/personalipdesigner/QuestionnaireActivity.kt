package com.example.personalipdesigner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuestionnaireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val nameInput: EditText = findViewById(R.id.qNameInput)
        val interestInput: EditText = findViewById(R.id.interestInput)
        val submitButton: Button = findViewById(R.id.submitButton)

        // Pre-fill name if passed from MainActivity
        intent.getStringExtra("name")?.let { nameInput.setText(it) }

        submitButton.setOnClickListener {
            val name = nameInput.text.toString()
            val interest = interestInput.text.toString()
            if (name.isNotBlank() && interest.isNotBlank()) {
                Thread {
                    val prompt = "Generate a short tagline for $name focused on $interest"
                    val response = LLMService.ask(prompt)
                    runOnUiThread {
                        Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                    }
                }.start()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
