package com.example.movies.paging

import retrofit2.Response
import java.lang.Exception

class paginationFactory<key, Item>(

    private val initPage: key,
    private inline val onloadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextPage: key) -> Response<Item>,
    private inline val getNextKey: suspend (item: Item) -> key,
    private inline val onError: suspend (Throwable) -> Unit,
    private inline val onsucssuc: suspend (item: Item, newPage: key) -> Unit

) : pagination<key, Item> {

    private var currentKey = initPage
    private var isMakeingRequest = false

    override suspend fun loadNexPage() {
        if (isMakeingRequest) {
            return
        } else {
            isMakeingRequest = true
        }

        try {
            val response = onRequest(currentKey)
            if (response.isSuccessful) {
                val nextKey = getNextKey(response.body()!!)
                onsucssuc(response.body()!!, nextKey)
                currentKey = nextKey
                onloadUpdated(true)
            } else {
                onError(Exception("Failed to load data"))
                onloadUpdated(false)
            }
        } catch (e: Exception) {
            onError(e)
            onloadUpdated(false)
        }

    }

    override fun reset() {
        currentKey = initPage
    }

}