package com.example.album.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.album.databinding.ListItemAlbumBinding

class AlbumsAdapter( private val onClickListener: OnClickListener ) :
    androidx.recyclerview.widget.ListAdapter<AlbumProperty,
            AlbumsAdapter.AlbumPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumPropertyViewHolder{
        return AlbumPropertyViewHolder(
            ListItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlbumPropertyViewHolder, position: Int) {
        val AlbumProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(AlbumProperty)
        }
        holder.bind(AlbumProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AlbumProperty>() {
        override fun areItemsTheSame(oldItem: AlbumProperty, newItem: AlbumProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AlbumProperty, newItem: AlbumProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class AlbumPropertyViewHolder(private var binding: ListItemAlbumBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(AlbumProperty: AlbumProperty) {
            binding.property = AlbumProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (albumProperty: AlbumProperty) -> Unit) {
        fun onClick(albumProperty: AlbumProperty) = clickListener(albumProperty)
    }
}