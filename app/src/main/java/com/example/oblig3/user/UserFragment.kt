package com.example.oblig3.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.oblig3.R
import com.example.oblig3.databinding.FragmentMainPageBinding


class UserFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)


        return binding.root
    }
}

