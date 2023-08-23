package com.yactayo.ecfinal_yactayo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class ListaModel(
    
    val name: String,
    val description: String,
    val images: Images,
    val introduction: Introduction

): Parcelable

@Parcelize
data class Images(

    val icon: String

): Parcelable

@Parcelize
data class Introduction(

    val text: String

): Parcelable
