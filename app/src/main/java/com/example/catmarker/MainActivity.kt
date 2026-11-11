package com.example.catmarker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvNomeJogadorUm: TextView
    private lateinit var tvNomeJogadorDois: TextView
    private var nomeUm: String = "Jogador 1"
    private var nomeDois: String = "Jogador 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        findViewById<Button>(R.id.btHistoricoJogadas).setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }
        tvNomeJogadorUm = findViewById(R.id.tvNomeJogadorUm)
        tvNomeJogadorDois = findViewById(R.id.tvNomeJogadorDois)
    }
    fun btInformarNomesOnClick(view: View) {
        val intent = Intent(this, NomesActivity::class.java)
        intent.putExtra("nomeUm", nomeUm)
        intent.putExtra("nomeDois", nomeDois)
        getResult.launch(intent)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            resultado -> if (resultado.resultCode == RESULT_OK) {
            nomeUm = resultado.data?.getStringExtra("nomeUm") ?: "Jogador 1"
            nomeDois = resultado.data?.getStringExtra("nomeDois") ?: "Jogador 2"
            tvNomeJogadorUm.text = "$nomeUm:"
            tvNomeJogadorDois.text = "$nomeDois:"
        }
    }


}