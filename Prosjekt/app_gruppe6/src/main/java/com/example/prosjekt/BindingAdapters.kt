package com.example.prosjekt

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.prosjekt.albums.Album
import com.example.prosjekt.albums.AlbumsAdapter
import com.example.prosjekt.albums.JsonAlbumsApiStatus
import com.example.prosjekt.images.Image
import com.example.prosjekt.images.ImagesAdapter
import com.example.prosjekt.images.JsonImagesApiStatus
import com.example.prosjekt.users.UserApiStatus
import com.example.prosjekt.users.User
import com.example.prosjekt.users.UsersAdapter


@BindingAdapter("listUserData")
fun bindUserRecyclerView(
    recyclerView: RecyclerView,
    data: List<User>?
) {
    val adapter = recyclerView.adapter as UsersAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatusUser")
fun bindStatus(
    statusImageView: ImageView,
    status: UserApiStatus?
) {
    when (status) {
        UserApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        UserApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UserApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("listAlbumData")
fun bindAlbumRecyclerView(
    recyclerView: RecyclerView,
    data: List<Album>?
) {
    val adapter = recyclerView.adapter as AlbumsAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatusAlbums")
fun bindStatus1(
    statusImageView: ImageView,
    status: JsonAlbumsApiStatus?
) {
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

@BindingAdapter("listImageData")
fun bindImageRecyclerView(
    recyclerView: RecyclerView,
    data: List<Image>?
) {
    val adapter = recyclerView.adapter as ImagesAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val url = GlideUrl(
            imgUrl, LazyHeaders.Builder().addHeader("User-Agent", "android").build()
        )
        Glide.with(imgView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
    if (imgUrl == null) {
        Glide.with(imgView.context)
            .load(R.drawable.deleted_photo_vektor)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("apiStatusPhoto")
fun bindStatus1(
    statusImageView: ImageView,
    status: JsonImagesApiStatus?
) {
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

