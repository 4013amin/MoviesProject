package com.example.movies.paging

interface pagination<key , Item> {

    suspend fun loadNexPage()

    fun reset()



}