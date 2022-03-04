package fr.epsi.rendu_epsi_b3

import com.google.android.gms.maps.model.LatLng

class City(
    val name: String,
    val description: String,
    val imgUrl: String,
    val address: String,
    val zipcode: String,
    val city: String,
    val cityLatLng: LatLng,
) {
}