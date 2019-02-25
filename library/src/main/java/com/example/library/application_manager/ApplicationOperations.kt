package com.example.library.application_manager

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import com.example.library.util.FileUtility
import com.example.library.database.DatabaseHelper
import com.example.library.util.ImageUtility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class ApplicationOperations {

    val applicationList = ArrayList<ApplicationModal>()

    companion object {
        var TABLE_NAME = ""
        var COLUMN_DATA = ""
        var ICON_CACHE = false
    }


    fun getAllApplications(
        context: Context,
        displayDialog: Boolean,
        onApplicationsGetListener: OnApplicationsGetListener?
    ) {
        getApplications(context, displayDialog, onApplicationsGetListener, "both")
    }

    fun getAllInstalledApplications(
        context: Context,
        displayDialog: Boolean,
        onApplicationsGetListener: OnApplicationsGetListener?
    ) {
        getApplications(context, displayDialog, onApplicationsGetListener, "installed")
    }

    fun getAllSystemApplications(
        context: Context,
        displayDialog: Boolean,
        onApplicationsGetListener: OnApplicationsGetListener?
    ) {
        getApplications(context, displayDialog, onApplicationsGetListener, "system")
    }

    private fun getApplications(
        context: Context,
        displayDialog: Boolean,
        onApplicationsGetListener: OnApplicationsGetListener?,
        s: String
    ) {
        try {
            val packageManager = context.packageManager
            val applicationModalListAdded = ArrayList<ApplicationModal>()
            val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//            val dialog = Dialog(context)
//            val view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null)
//            dialog.setContentView(view)

            doAsync {
                // do your background thread task
                for (packageInfo in packages) {
                    val applicationModal = ApplicationModal()

                    if (s.equals("installed")) {
                        if (isSystemPackage(packageManager.getPackageInfo(packageInfo.packageName, 0))) {
                            continue
                        }

                    } else if (s.equals("system")) {
                        if (!isSystemPackage(packageManager.getPackageInfo(packageInfo.packageName, 0))) {
                            continue
                        }
                    } else {
                    }

                    applicationModal.isSystemApp =
                        isSystemPackage(packageManager.getPackageInfo(packageInfo.packageName, 0))
                    applicationModal.packageName = packageInfo.packageName
                    applicationModal.appLink = "https://play.google.com/store/apps/details?id=" +
                            applicationModal.packageName
                    applicationModal.sourceDir = packageInfo.sourceDir
                    val file = File(applicationModal.sourceDir)
                    applicationModal.size = file.length().toLong()
                    applicationModal.sizeReadable = FileUtility().getFileSize(file)
                    applicationModal.applicationLabel = packageInfo.loadLabel(packageManager).toString()
                    applicationModal.versionName = packageManager.getPackageInfo(packageInfo.packageName, 0).versionName
                    applicationModal.versionCode =
                        packageManager.getPackageInfo(packageInfo.packageName, 0).versionCode.toString()


                    if (ICON_CACHE) {
                        val bitmap = ImageUtility().getBitmapFromDrawable(packageInfo.loadIcon(packageManager))
                        val thumbStorageDirectory = "${context.cacheDir} /thumbnails"
                        if (!File(thumbStorageDirectory).exists())
                            File(thumbStorageDirectory).mkdirs()

                        val fileIcon = File(
                            thumbStorageDirectory,
                            "${applicationModal.applicationLabel}.png"
                        )
                        var outStream: FileOutputStream? = null
                        try {
                            outStream = FileOutputStream(fileIcon)
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                            outStream.flush()
                            outStream.close()

                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        applicationModal.icon = Uri.fromFile(fileIcon).toString()

                    } else {
                        applicationModal.icon = ImageUtility().bitmapToBase64(
                            ImageUtility().getBitmapFromDrawable(
                                packageInfo.loadIcon(packageManager)
                            )
                        )

                    }

                    applicationModal.firstInstallTime =
                        packageManager.getPackageInfo(packageInfo.packageName, 0).firstInstallTime.toString()
                    applicationModal.lastUpdateTime =
                        packageManager.getPackageInfo(packageInfo.packageName, 0).lastUpdateTime.toString()

                    applicationModal.dataDir = packageInfo.dataDir
                    applicationModal.deviceProtectedDataDir = packageInfo.deviceProtectedDataDir

                    applicationModalListAdded.add(applicationModal)
                }

                if (TABLE_NAME.isNotEmpty() && COLUMN_DATA.isNotEmpty()) {
                    DatabaseHelper.getInstance(context).clearTable(TABLE_NAME)

                    val listColumn = ArrayList<String>()
                    listColumn.add(COLUMN_DATA)

                    val listValue = ArrayList<Any>()
                    val listType = object : TypeToken<ArrayList<ApplicationModal>>() {}.type
                    listValue.add(Gson().toJson(applicationModalListAdded, listType))
                    listValue.add(Gson().toJson(applicationModalListAdded))
                    DatabaseHelper.getInstance(context).insert(TABLE_NAME, listColumn, listValue)

                }

                if (onApplicationsGetListener != null)
                    onApplicationsGetListener.onApplicationGet(applicationModalListAdded)

            }
        } catch (e: Exception) {
//            Crashlytics.logException(e)
        }
    }

    fun storeInDB(table: String, column: String) {
        TABLE_NAME = table
        COLUMN_DATA = column
    }

    fun isAppInstalledOrNot(context: Context, packageName: String): Boolean {
        try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

    private fun isSystemPackage(packageInfo: PackageInfo): Boolean {
        return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    interface OnApplicationsGetListener {
        fun onApplicationGet(applicationModalList: ArrayList<ApplicationModal>)
    }


}