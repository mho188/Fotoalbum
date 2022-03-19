package com.example.oblig3

import android.text.Html
import android.text.Spanned
import com.example.oblig3.network.UserProperty

fun formatPostItems(posts: List<UserProperty>): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<h2>BRUKERE</h2>")
        posts.forEach {

            append("<b>NAME: ${it.name}</b><br>")
            append("<b>ID: ${it.id}</b><br>")
            append("<b>EMAIL: ${it.email}</b><br><br><br>")

        }
    }
    return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
}