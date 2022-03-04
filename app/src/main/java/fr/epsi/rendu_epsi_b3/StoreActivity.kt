package fr.epsi.rendu_epsi_b3

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import java.io.IOException


class StoreActivity : Fragment() {
    lateinit var googleMap: GoogleMap

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingPermission", "UseRequireInsteadOfGet")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                googleMap.isMyLocationEnabled = true
                // Only approximate location access granted.
            }
            else -> {
                // No location access granted.
            }
        }
    }

    val cities = "{\n" +
            "    \"stores\": [\n" +
            "        {\n" +
            "            \"storeId\":3456,\n" +
            "            \"name\": \"Le Grand marché\",\n" +
            "            \"description\": \"situé au centre de la capitale\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkZLcXb2slwlM0RGjyuiAj5jdF1_qrN-MBcA&usqp=CAU\",\n" +
            "            \"address\": \"10 Rue Merlot\",\n" +
            "            \"zipcode\":\"75000\",\n" +
            "            \"city\":\"Paris\",\n" +
            "            \"longitude\":2.338646,\n" +
            "            \"latitude\":48.854885\n" +
            "        },\n" +
            "        {\n" +
            "        \"storeId\":9566,\n" +
            "            \"name\": \"Marché de Bordeaux\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"33000\",\n" +
            "            \"city\":\"Bordeaux\",\n" +
            "            \"longitude\":-0.57918,\n" +
            "            \"latitude\":44.837789\n" +
            "        }\n" +
            "        ,\n" +
            "        {\n" +
            "        \"storeId\":3566,\n" +
            "            \"name\": \"Marché de Pau\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"33000\",\n" +
            "            \"city\":\"Pau\",\n" +
            "            \"longitude\":43.293295,\n" +
            "            \"latitude\":-0.363570\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":3356,\n" +
            "            \"name\": \"Le Grand marché Lille\",\n" +
            "            \"description\": \"situé au centre de la capitale\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkZLcXb2slwlM0RGjyuiAj5jdF1_qrN-MBcA&usqp=CAU\",\n" +
            "            \"address\": \"10 Rue Merlot\",\n" +
            "            \"zipcode\":\"75000\",\n" +
            "            \"city\":\"Lille\",\n" +
            "            \"longitude\":3.063295,\n" +
            "            \"latitude\":50.608719\n" +
            "        },\n" +
            "        {\n" +
            "        \"storeId\":9536,\n" +
            "            \"name\": \"Marché de Nantes\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"33000\",\n" +
            "            \"city\":\"Nantes\",\n" +
            "            \"longitude\":-0.57918,\n" +
            "            \"latitude\":44.837789\n" +
            "        }\n" +
            "        ,\n" +
            "        {\n" +
            "        \"storeId\":9562,\n" +
            "            \"name\": \"Marché de Toulouse\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"31000\",\n" +
            "            \"city\":\"Toulouse\",\n" +
            "            \"longitude\":1.411209,\n" +
            "            \"latitude\":43.533513\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    private val callback = OnMapReadyCallback { googleMap ->

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://djemam.com/epsi/stores.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        val cityarray = arrayListOf<City>()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                val a=1
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null){
                    val jsonCities = JSONObject(data)
                    val jsArray = jsonCities.getJSONArray("stores")
                    for (i in 0 until jsArray.length()){
                        val jsonObject = jsArray.getJSONObject(i)
                        val cityLatLng = LatLng(jsonObject.optDouble("latitude",0.0),jsonObject.optDouble("longitude",0.0))
                        val address = jsonObject.getString("address")
                        val cityName = jsonObject.getString("city")
                        val zip = jsonObject.getString("zipcode")
                        val urlImg = jsonObject.getString("pictureStore")
                        val description = jsonObject.getString("description")
                        val name = jsonObject.getString("name")
                        val city = City(name, description, urlImg, address, zip, cityName, cityLatLng)
                        cityarray.add(city)
                        activity?.runOnUiThread(){
                        googleMap.addMarker(MarkerOptions().position(cityLatLng).title(jsonObject.optString(name,"")).snippet(address + zip + cityName))
                        }
                    }
                }

            }
        })
//        val jsCities = JSONObject(cities)
//        val items = jsCities.getJSONArray("stores")
//        for (i in 0 until items.length()) {
//            val jsonObject = items.getJSONObject(i)
//            val address = jsonObject.getString("address")
//            val cityName = jsonObject.getString("city")
//            val zip = jsonObject.getString("zipcode")
//            val urlImg = jsonObject.getString("pictureStore")
//            val description = jsonObject.getString("description")
//            val name = jsonObject.getString("name")
//            val cityLatLng = LatLng(
//                jsonObject.optDouble("latitude", 0.0),
//                jsonObject.optDouble("longitude", 0.0)
//            )
//            googleMap.addMarker(
//                MarkerOptions().position(cityLatLng).title(jsonObject.optString("name", ""))
//                    .snippet(address + zip + cityName)
//            )
//        }

        this.googleMap = googleMap
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
//    googleMap.setOnMarkerClickListener(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


}




