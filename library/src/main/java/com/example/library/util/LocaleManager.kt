package com.example.library.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.support.v7.app.AppCompatActivity
import com.example.common.util.SharedPreferenceUtility
import java.util.*

/**
 * how to use LocaleManager
 *
 * Step-1: Extend BaseActivity class extend in your activity/fragment
 * Step-2: Open ApplicationClass
 *          attachBaseContext() override method inside "super.attachBaseContext(new LocaleManager().setLocale(base));" add this line
 *          onConfigurationChanged() override method inside "super.onConfigurationChanged(newConfig);
 *                                                          new LocaleManager().setLocale(this);" add this line
 *Step-3: Change Language using this lines
 *          new LocaleManager().setNewLocale(MainActivity.this, "fr");
            recreate();
 */

class LocaleManager {

    val SELECTED_LANGUAGE = "selected_language"

    companion object {
        val DEFAULT_LANGUAGE = "en"
    }

    fun setLocale(context: Context): Context {
        return setNewLocale(context, getLanguage(context))
    }

    fun setNewLocale(context: Context, language: String): Context {
        persistLanguage(context, language)
        return updateResources(context, language)
    }

    private fun getLanguage(context: Context): String {
        return SharedPreferenceUtility().getData(context, SELECTED_LANGUAGE, DEFAULT_LANGUAGE).toString()
    }

    private fun persistLanguage(context: Context, language: String) {
        SharedPreferenceUtility().setData(context, SELECTED_LANGUAGE, language)
    }

    fun updateResources(context: Context, language: String): Context {

        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
            return context.createConfigurationContext(configuration)
        } else {
            configuration.locale = locale
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        return context
    }


    open class BaseActivity : AppCompatActivity() {
        override fun attachBaseContext(newBase: Context?) {
            super.attachBaseContext(LocaleManager().setLocale(newBase!!))
        }
    }
}