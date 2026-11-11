package com.example.catmarker

import androidx.appcompat.app.AlertDialog
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

    private var pontosJogadorUm: Int = 0
    private var pontosJogadorDois: Int = 0

    private lateinit var tvPontosJogadorUm: TextView
    private lateinit var tvPontosJogadorDois: TextView



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
        tvPontosJogadorUm = findViewById(R.id.tvPontosJogadorUm)
        findViewById<Button>(R.id.btJogadorUmMais1).setOnClickListener {adicionarPontosJogadorUm(1)}
        findViewById<Button>(R.id.btJogadorUmMais3).setOnClickListener {adicionarPontosJogadorUm(3)}
        findViewById<Button>(R.id.btJogadorUmMais6).setOnClickListener {adicionarPontosJogadorUm(6)}
        findViewById<Button>(R.id.btJogadorUmMais9).setOnClickListener {adicionarPontosJogadorUm(9)}
        findViewById<Button>(R.id.btJogadorUmMais12).setOnClickListener {adicionarPontosJogadorUm(12)}

        tvPontosJogadorDois = findViewById(R.id.tvPontosJogadorDois)
        findViewById<Button>(R.id.btJogadorDoisMais1).setOnClickListener {adicionarPontosJogadorDois(1)}
        findViewById<Button>(R.id.btJogadorDoisMais3).setOnClickListener {adicionarPontosJogadorDois(3)}
        findViewById<Button>(R.id.btJogadorDoisMais6).setOnClickListener {adicionarPontosJogadorDois(6)}
        findViewById<Button>(R.id.btJogadorDoisMais9).setOnClickListener {adicionarPontosJogadorDois(9)}
        findViewById<Button>(R.id.btJogadorDoisMais12).setOnClickListener {adicionarPontosJogadorDois(12)}

        findViewById<Button>(R.id.btZerarHistorico).setOnClickListener {
            pontosJogadorUm = 0
            pontosJogadorDois = 0
            tvPontosJogadorUm.text = pontosJogadorUm.toString()
            tvPontosJogadorDois.text = pontosJogadorDois.toString()
        }

        findViewById<Button>(R.id.btHistoricoJogadas).setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }




        tvNomeJogadorUm = findViewById(R.id.tvNomeJogadorUm)
        tvNomeJogadorDois = findViewById(R.id.tvNomeJogadorDois)
    }

    private fun adicionarPontosJogadorUm(Valor: Int) {
        pontosJogadorUm += Valor
        tvPontosJogadorUm.text = pontosJogadorUm.toString()
        verificarVencedor(nomeUm, pontosJogadorUm)
    }

    private fun adicionarPontosJogadorDois(Valor: Int) {
        pontosJogadorDois += Valor
        tvPontosJogadorDois.text = pontosJogadorDois.toString()
        verificarVencedor(nomeDois, pontosJogadorDois)

    }

    private fun verificarVencedor(nomeJogador: String, pontos: Int) {
        if (pontos>=12) {
            AlertDialog.Builder(this)
                .setTitle("Vencedor!")
                .setMessage("$nomeJogador venceu!")
                .setPositiveButton("OK") { dialog, _ ->
                    reiniciaPlacar()
                    dialog.dismiss()
                }

                .setNegativeButton("Cancelar", null)
                .setCancelable(false)
                .show()
            }
    }

    private fun reiniciaPlacar() {
        pontosJogadorUm = 0
        pontosJogadorDois = 0
        tvPontosJogadorUm.text = pontosJogadorUm.toString()
        tvPontosJogadorDois.text = pontosJogadorDois.toString()
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