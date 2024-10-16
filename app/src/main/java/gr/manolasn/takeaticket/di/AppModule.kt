package gr.manolasn.takeaticket.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.manolasn.takeaticket.App
import gr.manolasn.takeaticket.data.localDatabase.FavoritesDao
import gr.manolasn.takeaticket.data.localDatabase.FavoritesDatabase
import gr.manolasn.takeaticket.data.network.ApiCalls
import gr.manolasn.takeaticket.data.repository.FavoritesRepositoryImpl
import gr.manolasn.takeaticket.data.repository.HomeRepositoryImpl
import gr.manolasn.takeaticket.domain.repository.FavoritesRepository
import gr.manolasn.takeaticket.domain.repository.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApi(
    ): ApiCalls {
        return Retrofit.Builder().baseUrl(App.baseURL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            ).build().create(ApiCalls::class.java)
    }


    @Provides
    @Singleton
    fun provideFavoritesDatabase(
        @ApplicationContext context: Context
    ): FavoritesDatabase {
        return Room.databaseBuilder(
            context,
            FavoritesDatabase::class.java,
            "favorite_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavoritesDao(
        favoritesDatabase: FavoritesDatabase
    ): FavoritesDao {
        return favoritesDatabase.favoritesDao()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        apiCalls: ApiCalls,
    ): HomeRepository {
        return HomeRepositoryImpl(apiCalls)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        favoritesDao: FavoritesDao
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesDao)
    }


    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }


}