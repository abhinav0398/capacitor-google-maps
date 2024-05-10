package com.capacitorjs.plugins.googlemaps

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.capacitorjs.plugins.googlemaps.CapacitorGoogleMapMarker
import com.capacitorjs.plugins.googlemaps.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import org.json.JSONObject

class StopInfoWindow(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {
        val myMarker = marker.tag as? CapacitorGoogleMapMarker

        return myMarker?.let {
            val view = LayoutInflater.from(context).inflate(R.layout.stop_info_window, null)

            val infoWindowTitle = it.infoData?.getString("infoWindowTitle")
            val vehicleNumberOne = it.infoData?.getString("vehicleNumberOne")
            val etaOne = it.infoData?.getString("etaOne")
            val vehicleNumberTwo = it.infoData?.getString("vehicleNumberTwo")
            val etaTwo = it.infoData?.getString("etaTwo")
            val loading = it.infoData?.getBoolean("loading")
            val apiFail = it.infoData?.getBoolean("apiFail")

            val infoWindowTitleLayout = view.findViewById<TextView>(R.id.info_window_title)
            val vehicleNumberOneLayout = view.findViewById<TextView>(R.id.vehicle_number_one)
            val etaOneLayout = view.findViewById<TextView>(R.id.eta_one)
            val vehicleNumberTwoLayout = view.findViewById<TextView>(R.id.vehicle_number_two)
            val etaTwoLayout = view.findViewById<TextView>(R.id.eta_two)
            
            


            if(loading == true) {
                infoWindowTitleLayout.visibility = View.GONE;
                vehicleNumberOneLayout.visibility = View.GONE;
                etaOneLayout.visibility = View.GONE;
                vehicleNumberTwoLayout.visibility = View.GONE;
                etaTwoLayout.visibility = View.GONE;
            } else if( apiFail == true) {
                infoWindowTitleLayout.visibility = View.GONE;
                vehicleNumberOneLayout.visibility = View.GONE;
                etaOneLayout.visibility = View.GONE;
                vehicleNumberTwoLayout.visibility = View.GONE;
                etaTwoLayout.visibility = View.GONE;
            } else {
                infoWindowTitleLayout.text = infoWindowTitle;
                vehicleNumberOneLayout.text = vehicleNumberOne;
                etaOneLayout.text = etaOne;
                vehicleNumberTwoLayout.text = vehicleNumberTwo;
                etaTwoLayout.text = etaTwo;
            }



            view
        }
    }
}