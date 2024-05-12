package com.capacitorjs.plugins.googlemaps

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
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
            var view: View? = null;

            val viewType = it.infoData?.getString("viewType")
            val infoWindowTitle = it.infoData?.getString("infoWindowTitle")
            val vehicleNumberOne = it.infoData?.getString("vehicleNumberOne")
            val etaOne = it.infoData?.getString("etaOne")
            val vehicleNumberTwo = it.infoData?.getString("vehicleNumberTwo")
            val etaTwo = it.infoData?.getString("etaTwo")
            val loading = it.infoData?.getBoolean("loading")
            val apiFail = it.infoData?.getBoolean("apiFail")
            val busNumber = it.infoData?.getString("busNumber")
            val isBusItemTwo = it.infoData?.getBoolean("isBusItemTwo")

            view = if(viewType == "STOPS"){
                LayoutInflater.from(context).inflate(R.layout.stop_info_window, null);
            }else{
                LayoutInflater.from(context).inflate(R.layout.bus_info_window, null);
            }

            //STOP INFO WINDOW PARAMETERS
            val infoWindowTitleLayout = view.findViewById<TextView>(R.id.info_window_title)
            val vehicleNumberOneLayout = view.findViewById<TextView>(R.id.vehicle_number_one)
            val etaOneLayout = view.findViewById<TextView>(R.id.eta_one)
            val vehicleNumberTwoLayout = view.findViewById<TextView>(R.id.vehicle_number_two)
            val etaTwoLayout = view.findViewById<TextView>(R.id.eta_two)
            val isBusItemTwoLayout = view.findViewById<LinearLayout>(R.id.bus_item_two)
            val viewLineTwoLayout = view.findViewById<View>(R.id.view_line_two)
            val seeMoreBusesLayout = view.findViewById<Button>(R.id.see_more_buses)

            //BUS INFO WINDOW PARAMETERS
            val busNumberLayout = view.findViewById<TextView>(R.id.bus_number)


            if(loading == true) {
                infoWindowTitleLayout.visibility = View.GONE;
                vehicleNumberOneLayout.visibility = View.GONE;
                etaOneLayout.visibility = View.GONE;
                vehicleNumberTwoLayout.visibility = View.GONE;
                etaTwoLayout.visibility = View.GONE;
                busNumberLayout.visibility = View.GONE;
            } else if( apiFail == true) {
                infoWindowTitleLayout.visibility = View.GONE;
                vehicleNumberOneLayout.visibility = View.GONE;
                etaOneLayout.visibility = View.GONE;
                vehicleNumberTwoLayout.visibility = View.GONE;
                etaTwoLayout.visibility = View.GONE;
                busNumberLayout.visibility = View.GONE;
            } else {
                if(viewType == "STOPS"){
                    infoWindowTitleLayout.text = infoWindowTitle;
                    vehicleNumberOneLayout.text = vehicleNumberOne;
                    etaOneLayout.text = etaOne;
                    vehicleNumberTwoLayout.text = vehicleNumberTwo;
                    etaTwoLayout.text = etaTwo;
                    if(isBusItemTwo == false){
                        isBusItemTwoLayout.visibility = View.INVISIBLE;
                        viewLineTwoLayout.visibility = View.INVISIBLE;
                        seeMoreBusesLayout.visibility = View.INVISIBLE;
                    }
                }else{
                    busNumberLayout.text = busNumber;
                }


            }



            view
        }
    }
}