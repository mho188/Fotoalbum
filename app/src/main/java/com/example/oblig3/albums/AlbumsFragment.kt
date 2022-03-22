package com.example.album.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.album.databinding.FragmentAlbumsBinding

private lateinit var viewModel: AlbumsViewModel
private lateinit var viewModelFactory: AlbumsViewModelFactory

class AlbumsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentAlbumsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        val userProperty = AlbumsFragmentArgs.fromBundle(arguments!!).selectedUser
        viewModelFactory = AlbumsViewModelFactory(userProperty, application)

        viewModel = ViewModelProvider(
            this, viewModelFactory).get(AlbumsViewModel::class.java)

        binding.albumsViewModel = viewModel
        binding.albumList.adapter = AlbumsAdapter(AlbumsAdapter.OnClickListener {
            viewModel.displayAlbumDetails(it)
        })

        viewModel.navigateToSelectedAlbum.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(
                    AlbumsFragmentDirections.actionAlbumsToImages(it)
                )
                viewModel.displayAlbumDetailsComplete()
            }
        }

        return binding.root
    }
}