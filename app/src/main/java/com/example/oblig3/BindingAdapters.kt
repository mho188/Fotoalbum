package com.example.album

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.album.albums.AlbumProperty
import com.example.album.albums.AlbumsAdapter
import com.example.album.albums.JsonAlbumsApiStatus
import com.example.album.images.ImageProperty
import com.example.album.images.ImagesAdapter
import com.example.album.images.JsonImagesApiStatus
import com.example.album.users.JsonUsersApiStatus
import com.example.album.users.UserProperty
import com.example.album.users.UsersAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val url = GlideUrl(
            imgUrl, LazyHeaders.Builder().addHeader("User-Agent", "android").build())
        Glide.with(imgView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
    if (imgUrl==null) {
        Glide.with(imgView.context)
            .load(R.drawable.photo_deleted)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listUserData")
fun bindUserRecyclerView(
    recyclerView: RecyclerView,
    data: List<UserProperty>?
) {
    val adapter = recyclerView.adapter as UsersAdapter
    adapter.submitList(data)
}

@BindingAdapter("listAlbumData")
fun bindAlbumRecyclerView(
    recyclerView: RecyclerView,
    data: List<AlbumProperty>?
) {
    val adapter = recyclerView.adapter as AlbumsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listImageData")
fun bindImageRecyclerView(recyclerView: RecyclerView,
                          data: List<ImageProperty>?) {
    val adapter = recyclerView.adapter as ImagesAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatusUser")
fun bindStatus(statusImageView: ImageView,
               status: JsonUsersApiStatus?) {
    when (status) {
        JsonUsersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        JsonUsersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        JsonUsersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatusPhoto")
fun bindStatus1(statusImageView: ImageView,
               status: JsonImagesApiStatus?) {
    when (status) {
        JsonImagesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        JsonImagesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        JsonImagesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatusAlbums")
fun bindStatus1(statusImageView: ImageView,
                status: JsonAlbumsApiStatus?) {
    when (status) {
        JsonAlbumsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        JsonAlbumsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        JsonAlbumsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}