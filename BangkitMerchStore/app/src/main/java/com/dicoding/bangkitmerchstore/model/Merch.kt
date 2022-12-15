package com.dicoding.bangkitmerchstore.model

data class Merch(
    val id: Long,
    val image: Int,
    val title: String,
    val desc: String,
    val requiredPoint: Int,
)