package com.sample.spacexassignment.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.spacexassignment.data.model.DetailItem
import com.sample.spacexassignment.databinding.LayoutDetailItemRecyclerViewItemBinding

class DetailsRecyclerViewAdapter :
    RecyclerView.Adapter<DetailsRecyclerViewAdapter.BenefitsViewHolder>() {

    private var detailItems = mutableListOf<DetailItem>()

    inner class BenefitsViewHolder(val binding: LayoutDetailItemRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutDetailItemRecyclerViewItemBinding.inflate(inflater, parent, false)
        return BenefitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        holder.binding.tvKey.text = detailItems[position].key
        holder.binding.tvValue.text = detailItems[position].value
    }

    override fun getItemCount() = detailItems.size

    fun setData(detailItems: List<DetailItem>) {
        this.detailItems.clear()
        this.detailItems.addAll(detailItems)
        notifyDataSetChanged()
    }
}
