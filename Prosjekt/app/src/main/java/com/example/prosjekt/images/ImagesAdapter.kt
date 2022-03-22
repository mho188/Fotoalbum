package com.example.prosjekt.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prosjekt.databinding.ListItemImageBinding


class ImagesAdapter( private val onClickListener: OnClickListener ) :
    androidx.recyclerview.widget.ListAdapter<Image,
            ImagesAdapter.ImagePropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ImagePropertyViewHolder, position: Int) {
        val image = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(image)
        }
        holder.bind(image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesAdapter.ImagePropertyViewHolder{
        return ImagePropertyViewHolder(
            ListItemImageBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    class ImagePropertyViewHolder(private var binding: ListItemImageBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ImageProperty: Image) {
            binding.property = ImageProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (image: Image) -> Unit) {
        fun onClick(image: Image) = clickListener(image)
    }
}