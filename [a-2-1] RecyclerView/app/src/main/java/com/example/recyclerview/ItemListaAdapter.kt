package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemListViewBinding

class ItemListaAdapter :
    RecyclerView.Adapter<ItemListaAdapter.ItemLineViewHolder>() {

    private val items: MutableList<ItemModel> = mutableListOf()

    class ItemLineViewHolder(val itemHolder: ItemListViewBinding) :
        RecyclerView.ViewHolder(itemHolder.root) {
        fun bind(item: ItemModel) {
            itemHolder.txtTitle.text = item.item
            itemHolder.txtDetail.text = item.detalhe
            itemHolder.itemDetail.visibility =
                if (item.detailVisibility) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLineViewHolder =
        ItemLineViewHolder(
            ItemListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemLineViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemHolder.cardViewItem.setOnClickListener {
            items[position].detailVisibility = !items[position].detailVisibility
            notifyItemChanged(position)
        }
    }

    fun setList(newItems: List<ItemModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}