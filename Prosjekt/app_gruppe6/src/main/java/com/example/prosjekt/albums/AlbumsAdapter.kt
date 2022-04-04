package com.example.prosjekt.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prosjekt.databinding.ListItemAlbumBinding


class AlbumsAdapter( private val onClickListener: OnClickListener ) :
    androidx.recyclerview.widget.ListAdapter<Album,
            AlbumsAdapter.AlbumPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: AlbumPropertyViewHolder, position: Int) {
        val album = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(album)
        }
        holder.bind(album)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumPropertyViewHolder{
        return AlbumPropertyViewHolder(
            ListItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    class AlbumPropertyViewHolder(private var binding: ListItemAlbumBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(AlbumProperty: Album) {
            binding.property = AlbumProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (album: Album) -> Unit) {
        fun onClick(album: Album) = clickListener(album)
    }
}