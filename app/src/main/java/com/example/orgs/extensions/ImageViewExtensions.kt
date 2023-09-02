package com.example.orgs.extensions

import android.widget.ImageView
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url) {
        fallback(com.example.orgs.R.drawable.erro)
        error(com.example.orgs.R.drawable.erro)
        placeholder(com.example.orgs.R.drawable.placeholder)
    }
}