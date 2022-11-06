package br.com.xpe.xpepratica2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.xpe.xpepratica2.data.Contato
import br.com.xpe.xpepratica2.databinding.ListItemContatoBinding

class ContatoAdapter(
    val items: List<Contato>
) : RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    private lateinit var binding: ListItemContatoBinding

    inner class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(contato: Contato) {
            binding.apply {
                tvNome.text = contato.nome
                tvTel.text = contato.telefone
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        binding = ListItemContatoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ContatoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}