package com.example.oblig3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private val context: Context, private var list: MutableList<User>): RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.card_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersAdapter.MyViewHolder, position: Int) {
        val user = list[position]
        holder.name?.text = user.name
        holder.info1?.text = user.username + " | " + user.email
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView? = null
        var info1: TextView? = null
        var userID: Array<String>? = null

        init {
            name = view.findViewById(R.id.item_title)
            info1 = view.findViewById(R.id.item_detail)

            itemView.setOnClickListener {
                val position: String = itemId.toString()



                Toast.makeText(view.context, "tester $position", Toast.LENGTH_SHORT).show()




            }
        }
    }
}