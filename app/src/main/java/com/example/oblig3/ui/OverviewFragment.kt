package com.example.oblig3.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.oblig3.NowApplication
import com.example.oblig3.R
import com.example.oblig3.formatPostItems
import com.example.oblig3.network.UserProperty


class OverviewFragment : Fragment() {

    lateinit var tvPostList: TextView
    lateinit var tvStatus: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        tvPostList = view.findViewById(R.id.tvPostsList)
        tvStatus = view.findViewById(R.id.tvStatus)

        val viewModelFactory = NowCastViewModelFactory(application as NowApplication)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NowCastViewModel::class.java)

        val postObserver: LiveData<List<UserProperty>> = viewModel.properties
        postObserver.observe(viewLifecycleOwner, Observer {
            for (properties in it) {
                Log.d("WFA_LOG", properties.name)
            }
            tvPostList.text = formatPostItems(it)
        })
        val statusObserver: LiveData<NowCastApiStatus> = viewModel.status
        statusObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                NowCastApiStatus.DONE -> {
                    tvStatus.visibility = View.INVISIBLE
                    tvStatus.text = "Ferdig"
                }
                NowCastApiStatus.LOADING -> {
                    tvStatus.text = "Laster ..."
                    tvStatus.visibility = View.VISIBLE
                }
                NowCastApiStatus.ERROR -> {
                    tvStatus.visibility = View.VISIBLE
                    tvStatus.text = "Noe feilet!!"
                }
            }
        })
        return view
    }
}