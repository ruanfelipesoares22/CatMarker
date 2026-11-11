package com.example.catmarker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NomesActivity : AppCompatActivity() {
    private lateinit var etNomeJogadorUm: EditText
    private lateinit var etNomeJogadorDois: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nomes)

        etNomeJogadorUm = findViewById(R.id.etNomeJogadorUm)
        etNomeJogadorDois = findViewById(R.id.etNomeJogadorDois)
        val btConfirmarNomes = findViewById<Button>(R.id.btConfirmarNomes)

        etNomeJogadorUm.setText(intent.getStringExtra("nomeUm") ?: "Jogador 1")
        etNomeJogadorDois.setText(intent.getStringExtra("nomeDois") ?: "Jogador 2")
    }

    fun btConfirmarNomesOnClick(view: View) {
        val nome1 = etNomeJogadorUm.text.toString().trim()
        val nome2 = etNomeJogadorDois.text.toString().trim()

        val intent = Intent()
        intent.putExtra("nomeUm", nome1.ifEmpty { "Jogador 1" })
        intent.putExtra("nomeDois", nome2.ifEmpty { "Jogador 2" })
        setResult(RESULT_OK, intent)
        finish()
    }
}