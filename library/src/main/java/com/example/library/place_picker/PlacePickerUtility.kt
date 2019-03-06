package com.example.library.place_picker

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker

class PlacePickerUtility(val context: Context) {

    companion object {
        var REQUEST_CODE = -1
        var PLACE_PICK_LISTNER: PlacePickListener? = null
    }

    fun pickLocation(requestCode: Int, placePickListener: PlacePickListener) {
        REQUEST_CODE = requestCode
        PLACE_PICK_LISTNER = placePickListener
        val builder = PlacePicker.IntentBuilder()
        (context as Activity).startActivityForResult(builder.build(context), requestCode)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_CODE != -1 && REQUEST_CODE == requestCode) {
            if (data != null) {
                PLACE_PICK_LISTNER!!.onPickLocation(PlacePicker.getPlace(context, data))
            }
        }
    }

    interface PlacePickListener {
        fun onPickLocation(place: Place)
    }
}