package com.jantune.heartdisease.ui.view.main.identification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jantune.heartdisease.R
import com.jantune.heartdisease.data.model.IdentificationHistory
import com.jantune.heartdisease.databinding.ItemIdentificationBinding

class IdentificationAdapter :
    ListAdapter<IdentificationHistory, IdentificationAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemIdentificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val identification = getItem(position)
        holder.bind(identification)
    }

    class MyViewHolder(private val binding: ItemIdentificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(identificationItem: IdentificationHistory) {
            val context = binding.root.context

            binding.historyName.text = identificationItem.name
            binding.historyIdentification.text = identificationItem.identification
            binding.historyDate.text = identificationItem.dateTime


            if (identificationItem.identification == context.getString(R.string.negatif_result)) {
                binding.historyIdentification.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.negative_identification
                    )
                )
            } else {
                binding.historyIdentification.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.positif_identification
                    )
                )
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IdentificationHistory>() {
            override fun areItemsTheSame(
                oldItem: IdentificationHistory,
                newItem: IdentificationHistory
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: IdentificationHistory,
                newItem: IdentificationHistory
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}