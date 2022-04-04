package com.example.prosjekt.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prosjekt.databinding.FragmentImagesBinding

private lateinit var viewModel: ImagesViewModel
private lateinit var viewModelFactory: ImagesViewModelFactory

class ImagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentImagesBinding.inflate(inflater)

        binding.lifecycleOwner = this
        val albumProperty = ImagesFragmentArgs.fromBundle(requireArguments()).selectedAlbum
        viewModelFactory = ImagesViewModelFactory(albumProperty, application)

        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[ImagesViewModel::class.java]

        binding.imagesViewModel = viewModel
        binding.imagesList.adapter = ImagesAdapter(ImagesAdapter.OnClickListener {
            viewModel.displayImageDetails(it)
        })

        viewModel.navigateToSelectedImage.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController().navigate(
                    ImagesFragmentDirections.actionImagesToImageDisplay(it)
                )
                viewModel.displayImageDetailsComplete()
            }
        }
        return binding.root
    }
}