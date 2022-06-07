package dev.pegasus.imageslider.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import  dev.pegasus.imageslider.R
import  dev.pegasus.imageslider.databinding.ListItemHomeSliderBinding

class HomeSliderPagerAdapter : ListAdapter<Int, HomeSliderPagerAdapter.ViewHolderHomeSlider>(HomeSliderDiffUtil) {

    companion object {
        val HomeSliderDiffUtil = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolderHomeSlider(val binding: ListItemHomeSliderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHomeSlider {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListItemHomeSliderBinding>(layoutInflater, R.layout.list_item_home_slider, parent, false)
        return ViewHolderHomeSlider(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderHomeSlider, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            imageId = item
        }
    }
}