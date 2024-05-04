package com.example.mvvm_learning.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_learning.api.QuoteService
import com.example.mvvm_learning.db.QuoteDatabase
import com.example.mvvm_learning.models.QuoteList
import com.example.mvvm_learning.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {
    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }
        }
        else{
            val quotes= quoteDatabase.quoteDao().getQuotes()
            val quoteList  = QuoteList(1,1,1,quotes,1,1)
            quoteLiveData.postValue(quoteList)
        }

    }
}