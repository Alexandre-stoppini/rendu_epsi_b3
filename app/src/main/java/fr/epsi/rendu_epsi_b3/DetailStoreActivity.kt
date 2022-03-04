package fr.epsi.rendu_epsi_b3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailStoreActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_store)
        intent.getStringExtra("title")?.let{setHeaderTitle(it)}
        val imageViewDetail = findViewById<ImageView>(R.id.imageDetail)
        val addressViewDetail = findViewById<TextView>(R.id.addressDetail)
        val zipViewDetail = findViewById<TextView>(R.id.zipDetail)
        val cityViewDetail = findViewById<TextView>(R.id.cityDetail)
        val descriptionViewDetail = findViewById<TextView>(R.id.descriptionDetail)
        val urlImage= intent.getStringExtra("urlImage")
        Picasso.get().load(urlImage).into(imageViewDetail)
        addressViewDetail.setText(intent.getStringExtra("address"))
        zipViewDetail.setText(intent.getStringExtra("zip"))
        cityViewDetail.setText(intent.getStringExtra("city"))
        descriptionViewDetail.setText(intent.getStringExtra("description"))
    }


}