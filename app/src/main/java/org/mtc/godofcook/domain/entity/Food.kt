package org.mtc.godofcook.domain.entity

data class Food(
    val id: Int?,
    val name: String,
    val category: String,
    val subCategory: String,
    val recipe: String
)
