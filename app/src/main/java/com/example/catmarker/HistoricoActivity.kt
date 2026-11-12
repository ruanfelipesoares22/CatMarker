package com.example.catmarker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvNomeJogadorUm = findViewById<TextView>(R.id.tvNomeJogadorUm)
        val tvNomeJogadorDois = findViewById<TextView>(R.id.tvNomeJogadorDois)
        val tvPartidasJogadorUm = findViewById<TextView>(R.id.tvPartidasJogadorUm)
        val tvPartidasJogadorDois = findViewById<TextView>(R.id.tvPartidasJogadorDois)

        val nomeUm = intent.getStringExtra("nomeUm")
        val nomeDois = intent.getStringExtra("nomeDois")
        val vitoriasJogadorUm = intent.getIntExtra("vitoriasJogadorUm", 0)
        val vitoriasJogadorDois = intent.getIntExtra("vitoriasJogadorDois", 0)

        tvNomeJogadorUm.text = "$nomeUm:"
        tvNomeJogadorDois.text = "$nomeDois:"
        tvPartidasJogadorUm.text = vitoriasJogadorUm.toString()
        tvPartidasJogadorDois.text = vitoriasJogadorDois.toString()

    }
}