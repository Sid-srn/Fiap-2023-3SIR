package com.example.recyclerpaises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerpaises.databinding.PaisItemListBinding
import java.util.Locale

class PaisesAdapter : RecyclerView.Adapter<PaisesAdapter.PaisesHolder>() {

    private val paises: MutableList<PaisModel> = mutableListOf()

    class PaisesHolder(val itemHolder: PaisItemListBinding) : RecyclerView.ViewHolder(itemHolder.root) {
        fun bind(item: PaisModel) {
            itemHolder.txtPais.text = item.pais
            itemHolder.txtContinente.text = item.continente
            itemHolder.imageView.setImageResource(
            when{
                item.continente.lowercase(Locale.ROOT).contains("america") -> R.drawable.map_america
                item.continente.lowercase(Locale.ROOT).contains("asia") -> R.drawable.map_asia
                item.continente.lowercase(Locale.ROOT).contains("africa") -> R.drawable.map_africa
                item.continente.lowercase(Locale.ROOT).contains("europa") -> R.drawable.map_europa
                else -> R.drawable.map_empty
            }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesHolder {
        return PaisesHolder(
            PaisItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int =
        paises.count()

    override fun onBindViewHolder(holder: PaisesHolder, position: Int) {
        holder.bind(paises[position])
        holder.itemHolder.closeButton.setOnClickListener{
            removeListItem(paises[position])
        }
    }

    fun setList(newItems: List<PaisModel>) {
        paises.clear()
        paises.addAll(newItems)
        notifyDataSetChanged()
    }

    fun removeListItem(removed: PaisModel) {
        val removedIndex = paises.indexOf(removed)
        paises.remove(removed)
        notifyItemRemoved(removedIndex)
        notifyItemRangeChanged(removedIndex, paises.size);
    }

    fun addPais(pais:PaisModel){
        paises.add(pais)
        notifyItemInserted(paises.count())

    }
}