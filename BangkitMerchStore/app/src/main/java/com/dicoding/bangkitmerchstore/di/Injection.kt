package com.dicoding.bangkitmerchstore.di

import com.dicoding.bangkitmerchstore.data.BangkitRepository

object Injection {
    fun provideRepository(): BangkitRepository {
        return BangkitRepository.getInstance()
    }
}