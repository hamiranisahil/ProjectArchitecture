package com.example.library.util

import android.content.Context
import android.content.Intent
import android.support.v4.content.FileProvider
import com.example.library.application_manager.ApplicationOperations
import com.example.library.topsnackbar.MySnackbar
import java.io.File

class ShareUtility {

    fun shareUrlTextImageVideo(context: Context, text: String?, fileString: String?) {
        shareUrlTextImageVideo(context, text, fileString, null)
    }

    fun shareUrlTextImageVideo(context: Context, text: String?, fileString: String?, appPackageName: Array<String>?) {

        val intent = Intent(Intent.ACTION_SEND)
        if (text != null) {
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
        }
        if (fileString != null) {
            intent.type = "image/jpeg"
            intent.putExtra(
                    Intent.EXTRA_STREAM,
                    FileProvider.getUriForFile(
                            context,
                            context.applicationContext.packageName + ".my.package.name.provider",
                            File(fileString)
                    )
            )
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        if (appPackageName != null) {

            if (ApplicationOperations().isAppInstalledOrNot(context, appPackageName[1])) {
                intent.setPackage(appPackageName[1])
                context.startActivity(intent)

            } else {
                MySnackbar.create(context, "${appPackageName[0]} Application Not Installed", MySnackbar.GRAVITY_BOTTOM, MySnackbar.DURATION_LENGTH_LONG).show()
            }

        } else {
            context.startActivity(Intent.createChooser(intent, "Choose App to Share"))
        }
    }

    class AppPackageName {
        companion object {
            val PLAY_STORE = arrayOf("Play Store", "com.android.vending")
            val WHATSAPP = arrayOf("Whatsapp", "com.whatsapp")
            val FACEBOOK = arrayOf("Facebook", "com.facebook.katana")
            val TWITTER = arrayOf("Twitter", "com.twitter.android")
        }
    }

}