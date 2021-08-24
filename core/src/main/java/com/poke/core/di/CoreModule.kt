package com.poke.core.di

import android.app.Application
import androidx.paging.PagedList
import androidx.room.Room
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.poke.core.*
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.database.AppDatabase
import com.poke.core.data.database.dao.MoveDao
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.local.LocalSource
import com.poke.core.data.local.LocalSourceImpl
import com.poke.core.data.pagination.PokeBoundaryCallback
import com.poke.core.data.remote.PokeRemoteSource
import com.poke.core.data.remote.PokeRemoteSourceImpl
import com.poke.core.data.repo.PokeRepository
import com.poke.core.data.repo.PokeRepositoryImpl
import com.poke.core.data.service.PokeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
}

val coreModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "pokes")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePokeDao(database: AppDatabase): PokeDao {
        return database.pokeDao()
    }

    fun provideStatDao(database: AppDatabase): StatDao {
        return database.statDao()
    }

    fun provideMoveDao(database: AppDatabase): MoveDao {
        return database.moveDao()
    }

    fun provideRetrofit() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(httpClient.build())
            .build()
    }

    fun <T> provideService(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    fun providePagedListConfig() = PagedList.Config.Builder()
        .setPrefetchDistance(50)
        .setPageSize(20).build()

    single { providePagedListConfig() }
    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }
    single { provideStatDao(get()) }
    single { provideMoveDao(get())}

    factory {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }


    single<PokeRepository>{
        PokeRepositoryImpl(statDao = get(), moveDao = get())
    }

    single {
        PokeRemoteSourceImpl(
            service = get()
        )
    }

    single {
        PokeBoundaryCallback(
            pokeRemoteSource = get(),
            coroutineScope = get(),
            pokeDao = get(),
            statDao = get(),
            moveDao = get()
        )
    }

    single<PokeRemoteSource> {
        PokeRemoteSourceImpl(
            service = get(),
        )
    }

    single<LocalSource> {
        LocalSourceImpl(
            dao = get(),
            pagedListConfig = get(),
            boundaryCallback = get()
        )
    }

    viewModel {
        PokeViewModel(
            localSource = get()
        )
    }

    single { provideRetrofit() }
    single { provideService(get(), PokeService::class.java) }
}