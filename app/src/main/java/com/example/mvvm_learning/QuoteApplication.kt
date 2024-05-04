package com.example.mvvm_learning

import android.app.Application
import com.example.mvvm_learning.api.QuoteService
import com.example.mvvm_learning.api.RetrofitHelper
import com.example.mvvm_learning.db.QuoteDatabase
import com.example.mvvm_learning.repository.QuoteRepository

class QuoteApplication: Application() {
    lateinit var  quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService,database,applicationContext)

    }
}