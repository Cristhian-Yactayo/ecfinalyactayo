package com.yactayo.ecfinal_yactayo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yactayo.ecfinal_yactayo.databinding.ItemListaBinding
import com.yactayo.ecfinal_yactayo.model.ListaModel

class RVListaListAdapter(var listas: List<ListaModel>, val onClick: (ListaModel) -> Unit): RecyclerView.Adapter<ListaVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaVH {
        val binding = ItemListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListaVH(binding, onClick)
    }

    override fun getItemCount(): Int = listas.size

    override fun onBindViewHolder(holder: ListaVH, position: Int) {
        holder.bind(listas[position])
    }

}

class ListaVH(private val binding: ItemListaBinding, val onClick: (ListaModel) -> Unit): RecyclerView.ViewHolder(binding.root){

    fun bind(lista: ListaModel){

        Glide.with(binding.root.context)
            .load(lista.images.icon)
            .into(binding.imageView)
        binding.txtName.text = lista.name
        binding.txtDescripcion.text = lista.description
        binding.txtIntroduccion.text = lista.introduction.text
        binding.root.setOnClickListener {
            onClick(lista)
        }

    }

}
