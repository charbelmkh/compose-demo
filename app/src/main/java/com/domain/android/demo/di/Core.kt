package com.domain.android.demo.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val core = module {
    factory{
        Dispatchers.IO
    }
}
