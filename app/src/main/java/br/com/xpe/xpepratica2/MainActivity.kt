package br.com.xpe.xpepratica2

import android.content.Intent
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xpe.xpepratica2.data.Contato
import br.com.xpe.xpepratica2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val retornoActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETURN)) {
                    val contato = if (VERSION.SDK_INT >= 33) {
                        it.getParcelableExtra(
                            RETURN,
                            Contato::class.java
                        )
                    } else {
                        it.getParcelableExtra(RETURN)
                    }
                    viewModel.salvarContato(contato)
                }
            }
        } else {
            Log.e("Fabricio:: Error", "Houve um falha ao obter os dados da segunda activity.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarListeners()
        configurarObservers()
    }

    private fun configurarObservers() {
        viewModel.listaContatos.observe(this) {
            prepararRecyclerView(it)
        }
    }

    private fun prepararRecyclerView(contatos: List<Contato>?) {
        Log.d("Fabricio", contatos.toString())
        binding.rvLista.layoutManager = LinearLayoutManager(applicationContext)
        if (contatos.isNullOrEmpty()) {
            binding.tvQtdItens.text = getString(R.string.lista_vazia)
        } else {
            binding.apply {
                rvLista.adapter = ContatoAdapter(contatos)
                tvQtdItens.text = resources.getQuantityString(
                    R.plurals.quantidade_plural,
                    contatos.size,
                    contatos.size
                )
            }
        }
    }

    private fun configurarListeners() {
        configuraFab()
    }

    private fun configuraFab() {
        binding.floatingActionButton.setOnClickListener {
            abrirActivityCadastro()
        }
    }

    private fun abrirActivityCadastro() {
        Intent(this, CadastroActivity::class.java).let {
            retornoActivity.launch(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FABRICIO", "DESTROY")
    }

    companion object {
        const val tag = "ENVIO_DADOS"
        const val RETURN = "resposta_cadastro"
    }
}