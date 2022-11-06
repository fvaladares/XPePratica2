package br.com.xpe.xpepratica2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.xpe.xpepratica2.data.Contato

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _listaContatos = MutableLiveData<List<Contato>>(mutableListOf())
    val listaContatos: LiveData<List<Contato>> = _listaContatos

    init {
        Log.e("uai", "uai...")
        Log.e("uai", "${_listaContatos.value}")
    }

    fun salvarContato(contato: Contato?) {

        contato?.let {
            val lista = _listaContatos.value?.toMutableList() ?: mutableListOf()
            lista.add(contato)
            _listaContatos.value = lista
        }
//        if (contato != null) {
//            val lista = _listaContatos.value?.toMutableList() ?: mutableListOf()
//            lista.add(contato)
//            _listaContatos.value = lista
//        } else {
//            Log.e("FABRICIO", "ERRO")
//        }

    }
}