package com.example.library.ads

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.util.CustomAlertDialog
import com.example.library.R
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.custom_dialog_ad_view.view.*

class CustomAlertDialogWithBannerAd {


    fun showDialogWithTwoButton(
        context: Context,
        adView: AdView,
        title: String,
        message: String,
        positiveButtonLabel: String,
        negativeButtonLabel: String,
        alertTwoButtonClickListener: CustomAlertDialog.AlertTwoButtonClickListener
    ) {

        val builder = AlertDialog.Builder(context).setTitle(title).setMessage(message)

        val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_ad_view, null) as ViewGroup
        if (view.childCount > 0) {
            view.removeAllViews()
        }
        if (adView.parent != null) {
            (adView.parent as ViewGroup).removeView(adView)
        }
        view.cv_ad_view.addView(adView)

        builder.setView(view)

        builder.setPositiveButton(positiveButtonLabel) { dialog, which ->
            alertTwoButtonClickListener.onAlertClick(
                dialog,
                which,
                true
            )
        }
        builder.setNegativeButton(negativeButtonLabel) { dialog, which ->
            alertTwoButtonClickListener.onAlertClick(
                dialog,
                which,
                false
            )
        }
        builder.create().show()


    }
}