package com.jantune.heartdisease.ui.view.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jantune.heartdisease.data.model.Gejala
import com.jantune.heartdisease.databinding.ItemGejalaBinding

class GejalaAdapter() : ListAdapter<Gejala, GejalaAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemGejalaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val gejala = getItem(position)
        holder.bind(gejala)
    }

    class MyViewHolder(private val binding: ItemGejalaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gejala: Gejala) {
            binding.tvTitleGejala.text = gejala.title
            Glide.with(binding.root)
                .load(gejala.image)
                .circleCrop()
                .into(binding.ivGejala)
                .clearOnDetach()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Gejala>() {
            override fun areItemsTheSame(oldItem: Gejala, newItem: Gejala): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Gejala, newItem: Gejala): Boolean {
                return oldItem == newItem
            }
        }
    }
}