package gr.manolasn.takeaticket

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        application = this
        appContext = applicationContext
        val localeCode = Locale.getDefault().language
        deviceLanguage = localeCode
        versionName = BuildConfig.VERSION_NAME
        baseURL = BuildConfig.BASE_URL
        accessToken = BuildConfig.ACCESS_TOKEN
        imageUrl = BuildConfig.IMAGE_URL
    }

    companion object {

        lateinit var application: Application
        lateinit var appContext: Context
        lateinit var deviceLanguage: String
        lateinit var versionName: String
        lateinit var baseURL: String
        lateinit var accessToken: String
        lateinit var imageUrl: String

    }

    fun getAppContext(): Context? {
        return application.applicationContext
    }

    fun getVersionName(): String {
        return versionName
    }







}