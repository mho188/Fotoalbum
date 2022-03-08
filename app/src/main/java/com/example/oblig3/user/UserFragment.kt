package com.example.oblig3.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.oblig3.R


class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        //val binding: UserFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)



        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

