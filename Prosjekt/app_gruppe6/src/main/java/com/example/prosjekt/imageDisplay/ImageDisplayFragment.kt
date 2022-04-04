package com.example.prosjekt.imageDisplay

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.prosjekt.R
import com.example.prosjekt.databinding.FragmentImageDisplayBinding
import kotlinx.android.synthetic.main.fragment_image_display.*


private lateinit var viewModel: ImageDisplayViewModel
private lateinit var viewModelFactory: ImageDisplayViewModelFactory
private lateinit var binding: FragmentImageDisplayBinding

class ImageDisplayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        binding = FragmentImageDisplayBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val imageProperty = ImageDisplayFragmentArgs.fromBundle(requireArguments()).selectedImage
        viewModelFactory = ImageDisplayViewModelFactory(imageProperty, application)

        viewModel = ViewModelProvider(
            this, viewModelFactory)[ImageDisplayViewModel::class.java]


        binding.imageDisplayViewModel = ViewModelProvider(
            this, viewModelFactory)[ImageDisplayViewModel::class.java]

        binding.changeImageDisplayButton.setOnClickListener { changeImageDisplayTitle() }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_button -> deleteImage()
        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_delete_image, menu)
    }

    private fun changeImageDisplayTitle(){
        binding.apply {
            changeImageDisplayButton.text = "Save"
            imageDisplayTitle.visibility = View.GONE
            editImageDisplayTitle.visibility = View.VISIBLE
            editImageDisplayTitle.requestFocus()
            changeImageDisplayButton.setOnClickListener { saveImageDisplayTitle(it) }
        }
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editImageDisplayTitle, 0)
    }

    private fun deleteImage() {
        viewModel.deleteImage()
        change_image_display_button.visibility = View.GONE
        edit_image_display_title.visibility = View.GONE
    }

    private fun saveImageDisplayTitle(view: View){
        binding.apply{
            invalidateAll()
            changeImageDisplayButton.text = "Edit"
            imageDisplayTitle.visibility = View.VISIBLE
            editImageDisplayTitle.visibility = View.GONE
            changeImageDisplayButton.setOnClickListener { changeImageDisplayTitle() }
        }
        viewModel.patchEdits(binding.editImageDisplayTitle.text.toString())
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
