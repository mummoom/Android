package com.mummoom.md.data.remote.Ingredients

import com.mummoom.md.data.Ingredients.Ingredients

interface SearchView {
    fun onSearchLoading()
    fun onSearchSuccess(searchIngredients: ArrayList<Ingredients>)
    fun onSearchFailure(code: Int, message: String)
    fun onSearchNothing(message:String)
}