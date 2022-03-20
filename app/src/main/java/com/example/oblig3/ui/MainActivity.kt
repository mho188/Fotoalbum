package com.example.oblig3.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3.NowApplication
import com.example.oblig3.R
import com.example.oblig3.User
import com.example.oblig3.UsersAdapter
import com.example.oblig3.network.NowCastApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), UsersAdapter.OnItemClickListener{

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private var listUsers: MutableList<User> = mutableListOf<User>()
    private var adapter: UsersAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter(this, listUsers, this)

        recyclerView.adapter = adapter

        NowApplication().getRetrofitInstanceNewsNowCast().getUsers().enqueue(object : Callback<MutableList<User>> {

            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                val usersResponse = response.body()
                listUsers.clear()
                usersResponse?.let { listUsers.addAll(it)
                adapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                val message = t.localizedMessage
                Log.d("WFA_LOG", message)
            }
        })
    }

    override fun onItemClick(position: Int) {
        val userid = position + 1
        Toast.makeText(this, "User ID: $userid", Toast.LENGTH_SHORT).show()
    }
}