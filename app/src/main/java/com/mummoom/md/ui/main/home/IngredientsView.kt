package com.mummoom.md.ui.main.home

interface IngredientsView {
    fun onIngredientsLoading()
    fun onIngredientsSuccess()
    fun onIngredientsFailure(status: Int, message: String)
}