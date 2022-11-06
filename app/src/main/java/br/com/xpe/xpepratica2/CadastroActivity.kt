package br.com.xpe.xpepratica2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.xpe.xpepratica2.data.Contato
import br.com.xpe.xpepratica2.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Fabricio", intent.getIntExtra(MainActivity.tag, 0).toString())

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarBtnSalvar()
    }

    private fun configurarBtnSalvar() {
        binding.btnEnviar.setOnClickListener {
            gravarDados()
        }
    }

    private fun gravarDados() {
        val nome = binding.etNome.text.toString()
        val telefone = binding.etTel.text.toString()
        val item = Contato(
            nome = nome,
            telefone = telefone
        )

        Intent().apply {
            putExtra(MainActivity.RETURN, item)
            setResult(RESULT_OK, this)
        }
        finish()
    }
}