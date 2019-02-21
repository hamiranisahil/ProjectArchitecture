package com.example.library.api_call

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.library.R
import com.example.library.modals.CommonRes
import com.example.library.util.AppConfig
import com.google.gson.Gson

class HandleStatusCode(val context: Context, val rootView: View, val commonRes: CommonRes, val requestCode: Int, val retrofitResponseListener: ApiCall.RetrofitResponseListener) {

    init {

        removeNoDataIfFound()

        when (commonRes.statusCode) {
            AppConfig().STATUS_200 -> {
                retrofitResponseListener.onSuccess(Gson().toJson(commonRes), requestCode)
            }
            AppConfig().STATUS_204 -> {
                retrofitResponseListener.onSuccess(Gson().toJson(commonRes), requestCode)
            }
            AppConfig().STATUS_409 -> {
                retrofitResponseListener.onSuccess(Gson().toJson(commonRes), requestCode)
            }
            AppConfig().STATUS_404 -> {
                setNoDataFound()
            }

            AppConfig().STATUS_422 -> {
                showSnackbar()
            }
        }
    }

    private fun setNoDataFound() {
        LayoutInflater.from(context).inflate(R.layout.no_data_found, (rootView as ViewGroup).findViewWithTag("root_layout"), true)
    }

    private fun removeNoDataIfFound() {
        val constraintLayout = rootView.findViewWithTag("no_data_found") as ConstraintLayout?
        if (constraintLayout != null)
            (rootView.findViewWithTag("root_layout") as ViewGroup).removeView(constraintLayout);
    }

    private fun showSnackbar(){
        Toast.makeText(context, "MESSAGE: "+commonRes.message, Toast.LENGTH_LONG).show()
//        MySnackbar.create(
//                context,
//                commonRes.message,
//                MySnackbar.GRAVITY_BOTTOM,
//                MySnackbar.DURATION_LENGTH_LONG
//        ).show()
    }
}