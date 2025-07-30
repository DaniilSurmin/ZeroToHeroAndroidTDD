package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemViewBinding

class Adapter(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<ItemViewHolder>() {

    private val list = mutableListOf<ItemUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), fragmentManager)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(newList: List<ItemUi>) {
        val diffUtil = DiffUtilCallback(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)
    }
}

class ItemViewHolder(
    private val binding: ItemViewBinding,
    private val fragmentManager: FragmentManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemUi) {
        binding.elementTextView.text = item.text
        binding.root.setOnClickListener {
            DeleteBottomSheetFragment
                .newInstance(item.id)
                .show(fragmentManager, DeleteBottomSheetFragment.TAG)
        }
    }
}

private class DiffUtilCallback(
    private val oldList: List<ItemUi>,
    private val newList: List<ItemUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}