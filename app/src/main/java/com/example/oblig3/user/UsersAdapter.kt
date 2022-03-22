package com.example.album.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.album.databinding.ListItemUserBinding

class UsersAdapter( private val onClickListener: OnClickListener ) :
    androidx.recyclerview.widget.ListAdapter<UserProperty,
            UsersAdapter.UserPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UserPropertyViewHolder{
        return UserPropertyViewHolder(ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserPropertyViewHolder, position: Int) {
        val userProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(userProperty)
        }
        holder.bind(userProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserProperty>() {
        override fun areItemsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserProperty, newItem: UserProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class UserPropertyViewHolder(private var binding: ListItemUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userProperty: UserProperty) {
            binding.property = userProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (marsProperty:UserProperty) -> Unit) {
        fun onClick(marsProperty:UserProperty) = clickListener(marsProperty)
    }
}


