package com.sample.spacexassignment.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.spacexassignment.R
import com.sample.spacexassignment.data.model.LaunchListItem
import com.sample.spacexassignment.databinding.LayoutLaunchItemRecyclerViewItemBinding
import com.sample.spacexassignment.extension.listen
import com.sample.spacexassignment.listener.OnRecyclerViewItemClick

class LaunchRecyclerViewAdapter(private val onRecyclerViewItemClick: OnRecyclerViewItemClick?) :
    RecyclerView.Adapter<LaunchRecyclerViewAdapter.BenefitsViewHolder>() {

    private var launchListItems = mutableListOf<LaunchListItem>()

    inner class BenefitsViewHolder(val binding: LayoutLaunchItemRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutLaunchItemRecyclerViewItemBinding.inflate(inflater, parent, false)
        return BenefitsViewHolder(binding).listen { position, type ->
            onRecyclerViewItemClick?.onItemClick(position)
        }
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        launchListItems[position].imageUrl?.let {
            Glide.with(holder.binding.ivPatch).load(it).centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24).into(holder.binding.ivPatch)
        } ?: holder.binding.ivPatch.setImageResource(R.drawable.ic_baseline_image_24)
        holder.binding.tvMissionName.text = launchListItems[position].missionName
        if (holder.binding.tvDateOfLaunch != null) {
            holder.binding.tvDateOfLaunch.text = launchListItems[position].dateOfLaunch
            holder.binding.tvRocketName?.text = launchListItems[position].rocketName
            holder.binding.tvSiteName?.text = launchListItems[position].launchSiteName
        }

        holder.binding.root.setBackgroundColor(
            ContextCompat.getColor(
                holder.binding.root.context,
                if (launchListItems[position].isSelected) R.color.highlight else android.R.color.transparent
            )
        )
    }

    override fun getItemCount() = launchListItems.size

    fun setData(launchListItems: List<LaunchListItem>) {
        this.launchListItems.clear()
        this.launchListItems.addAll(launchListItems)
        notifyDataSetChanged()
    }

    fun highlightItem(position: Int) {
        this.launchListItems.forEachIndexed { index, launchListItem ->
            launchListItem.isSelected = index == position
        }
        notifyDataSetChanged()
    }
}
