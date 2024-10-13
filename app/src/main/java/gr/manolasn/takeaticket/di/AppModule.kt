package gr.manolasn.takeaticket.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.manolasn.takeaticket.App
import gr.manolasn.takeaticket.data.network.ApiCalls
import gr.manolasn.takeaticket.data.repository.HomeRepositoryImpl
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
    fun provideHomeRepository(
        apiCalls: ApiCalls,
    ): HomeRepository {
        return HomeRepositoryImpl(apiCalls)
    }


    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }


}