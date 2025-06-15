package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.RecyclerItemBinding

class CustomRecyclerAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private val itemsList = ArrayList<CharSequence>()

    fun add(source: CharSequence) {
        itemsList.add(source)
        notifyItemInserted(itemsList.size - 1)
    }

    fun save (bundle: Bundle) {
        bundle.putCharSequenceArrayList(KEY, itemsList)
    }

    fun restore (bundle: Bundle) {
        itemsList.addAll(bundle.getCharSequenceArrayList(KEY) ?: ArrayList())
        notifyItemRangeInserted(0, itemsList.size)
    }

    companion object{
        private const val KEY = "CustomRecyclerAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(itemsList[position])
    }
}

class MyViewHolder(private val binding: RecyclerItemBinding) : ViewHolder(binding.root) {
    fun bind(source: CharSequence) {
        binding.elementTextView.text = source
    }
}