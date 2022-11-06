package br.com.xpe.xpepratica2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contato(
    val nome: String,
    val telefone: String
) : Parcelable
