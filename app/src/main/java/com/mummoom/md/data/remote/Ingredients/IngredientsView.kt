package com.mummoom.md.data.remote.Ingredients

import com.mummoom.md.data.Ingredients.Ingredients

interface IngredientsView {
    fun onIngredientsLoading()
    fun onIngredientsSuccess(ingredients : ArrayList<Ingredients>)
    fun onIngredientsFailure(code: Int, message: String)
}