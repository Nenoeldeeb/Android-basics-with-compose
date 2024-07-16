package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.di.AppContainer
import com.example.bookshelf.data.di.DefaultAppContainer

class BookshelfApplication: Application() {
	lateinit var container: AppContainer
	override fun onCreate() {
		super.onCreate()
		container = DefaultAppContainer()
	}
}