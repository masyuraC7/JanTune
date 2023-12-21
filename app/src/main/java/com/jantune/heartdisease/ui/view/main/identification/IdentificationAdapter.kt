package com.jantune.heartdisease.ui.view.main.identification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jantune.heartdisease.R
import com.jantune.heartdisease.data.remote.response.IdentificationItemResponse
import com.jantune.heartdisease.databinding.ItemIdentificationBinding
import com.jantune.heartdisease.utils.DateFormatter

class IdentificationAdapter(
    private val onItemClick: (IdentificationItemResponse) -> Unit,
    private val onIvDelete: (identificationId: Int) -> Unit
) : ListAdapter<IdentificationItemResponse, IdentificationAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemIdentificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val identification = getItem(position)
        holder.bind(identification, onIvDelete)
        holder.itemView.setOnClickListener {
            onItemClick(identification)
        }
    }

    class MyViewHolder(private val binding: ItemIdentificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            identificationItem: IdentificationItemResponse,
            onIvDelete: (identificationId: Int) -> Unit
        ) {
            val context = binding.root.context

            binding.historyName.text = identificationItem.name
            binding.historyIdentification.text = identificationItem.result
            binding.historyDate.text = DateFormatter.formatDate(identificationItem.date.toString())

            if (identificationItem.result == context.getString(R.string.negatif_result)) {
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

            binding.ivDelete.setOnClickListener {
                identificationItem.id?.let { it1 -> onIvDelete(it1) }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IdentificationItemResponse>() {
            override fun areItemsTheSame(
                oldItem: IdentificationItemResponse,
                newItem: IdentificationItemResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: IdentificationItemResponse,
                newItem: IdentificationItemResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}