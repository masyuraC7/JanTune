package com.jantune.heartdisease.ui.view.main.identification.saran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jantune.heartdisease.data.model.Saran
import com.jantune.heartdisease.databinding.ItemSaranBinding

class SaranAdapter : ListAdapter<Saran, SaranAdapter.MyViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSaranBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val saran = getItem(position)
        holder.bind(saran)
    }

    class MyViewHolder(private val binding: ItemSaranBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(saran: Saran) {
            binding.tvTitleSaran.text = saran.title
            binding.tvDescriptionSaran.text = saran.description
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Saran>() {
            override fun areItemsTheSame(oldItem: Saran, newItem: Saran): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Saran, newItem: Saran): Boolean {
                return oldItem == newItem
            }
        }
    }

}