package com.example.album.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.album.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by lazy {
        ViewModelProvider(this).get(UsersViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentUsersBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.usersViewModel = viewModel

        binding.userList.adapter = UsersAdapter(UsersAdapter.OnClickListener {
            viewModel.displayUserDetails(it)
        })

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(
                    UsersFragmentDirections.actionUsersToAlbums(it)
                )
                viewModel.displayUserDetailsComplete()
            }
        }

        return binding.root
    }
}