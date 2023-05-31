package com.george_georgy.memorykeeper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.george_georgy.memorykeeper.databinding.MemoryListItemBinding
import com.george_georgy.memorykeeper.model.Memory


class MemoryListAdapter :RecyclerView.Adapter<MemoryListAdapter.MemoryViewHolder>(){

    inner class MemoryViewHolder(private val binding: MemoryListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindNote(memory: Memory){
            binding.memoryTitle.text = memory.memoryTitle
            binding.memoryDetails.text = memory.memoryText
        }
    }

    private val diffCallback = object :DiffUtil.ItemCallback<Memory>(){

        override fun areItemsTheSame(oldItem: Memory, newItem: Memory): Boolean {
            return oldItem.memoryId == newItem.memoryId
        }

        override fun areContentsTheSame(oldItem: Memory, newItem: Memory): Boolean {
            return oldItem == newItem
        }

    }

    // we can use this differ to get list or submit items to list.
    val differ = AsyncListDiffer(this,diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        return MemoryViewHolder(
            MemoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val memory = differ.currentList[position]
        holder.bindNote(memory)

        holder.itemView.setOnClickListener {
            onClick?.invoke(memory)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // lambda expression
    var onClick : ((Memory) -> Unit)? = null


}