package br.com.fiap.ex3rickmorty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.ex3rickmorty.R
import br.com.fiap.ex3rickmorty.databinding.RickMortyListItemBinding
import br.com.fiap.ex3rickmorty.model.IFavoriteRickMorty
import br.com.fiap.ex3rickmorty.model.ResultModel
import com.squareup.picasso.Picasso

class RickMortyAdapter (private val delegate: IFavoriteRickMorty) :
    RecyclerView.Adapter<RickMortyAdapter.RickMortyViewHolder>() {

    private val rickMortyItems: MutableList<ResultModel> = mutableListOf()

    class RickMortyViewHolder(val itemHolder: RickMortyListItemBinding) :
        RecyclerView.ViewHolder(itemHolder.root) {
        fun bind(item: ResultModel) {
            Picasso.with(itemView.context).load(item.image).into(itemHolder.imgPicture)
            itemHolder.txtName.text =  item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        return RickMortyViewHolder(
            RickMortyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RickMortyViewHolder, position: Int) {
        holder.bind(rickMortyItems[position])
        holder.itemHolder.itemCard.setOnClickListener {
            delegate.detailItem(rickMortyItems[position])
        }
    }

    override fun getItemCount(): Int {
        return rickMortyItems.size
    }

    fun setList(newItems: List<ResultModel>) {
        rickMortyItems.clear()
        rickMortyItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addListItem(newItem: ResultModel) {
        rickMortyItems.add(newItem)
        notifyItemInserted(rickMortyItems.size)
    }

    fun removeListItem(removed: ResultModel) {
        val removedIndex = rickMortyItems.indexOf(removed)
        rickMortyItems.remove(removed)
        notifyItemRemoved(removedIndex)
        notifyItemRangeChanged(removedIndex, rickMortyItems.size);
    }

    fun editListItem(newItem: ResultModel, position:Int) {
        rickMortyItems[position] = newItem
        notifyItemChanged(position)
    }
}
