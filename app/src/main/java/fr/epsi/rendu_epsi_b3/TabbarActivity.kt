package fr.epsi.rendu_epsi_b3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class TabbarActivity : BaseActivity() {
    val CardTab = CardFragment()
    val OfferTab = OfferFragment()
    val StoreTab = StoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val tab_card = findViewById<TextView>(R.id.tab_card)
        val tab_offer = findViewById<TextView>(R.id.tab_offer)
        val tab_store = findViewById<TextView>(R.id.tab_store)
        val modif_user = findViewById<ImageView>(R.id.button_to_modif)

        modif_user.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application, ModifCompteActivity::class.java)
            startActivity(newIntent)
        })


        tab_card.setOnClickListener(View.OnClickListener {
            show_card()
        })
        tab_offer.setOnClickListener(View.OnClickListener {
            show_offer()
        })
        tab_store.setOnClickListener(View.OnClickListener {
            show_store()
        })
        show_card()
    }

    private fun show_card() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.contentLayout, CardFragment::class.java, null)
        trans.setReorderingAllowed(true)
        trans.addToBackStack("")
        trans.commit()
    }

    private fun show_offer() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.contentLayout, OfferFragment::class.java, null)
        trans.setReorderingAllowed(true)
        trans.addToBackStack("")
//        val offers = arrayListOf<Offer>()
//
//        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
//        val mRequestURL = "https://djemam.com/epsi/offers.json"
//        val request = Request.Builder()
//            .url(mRequestURL)
//            .get()
//            .cacheControl(CacheControl.FORCE_NETWORK)
//            .build()
//
//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                val a=1
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val data = response.body?.string()
//                if (data != null){
//                    val jsonObject = JSONObject(data)
//                    val jsArray = jsonObject.getJSONArray("items")
//                    for (i in 0 until jsArray.length()) {
//                        val jsOffer = jsArray.getJSONObject(i)
//                        val name = jsOffer.optString("name", "")
//                        val description = jsOffer.optString("description", "")
//                        val picture_url = jsOffer.optString("picture_url", "")
//                        val offer = Offer(name, description, picture_url)
//                        offers.add(offer)
//                    }
//
//                }
//
//            }
//        })
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewOffer)
//        recyclerView.layoutManager = LinearLayoutManager(this@TabbarActivity)
//        val offerAdaptater = OfferAdaptater(this, offers)
//        recyclerView.adapter = offerAdaptater
        trans.commit()
    }

    private fun show_store() {
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.contentLayout, StoreFragment::class.java, null)
        trans.setReorderingAllowed(true)
        trans.addToBackStack("")
        trans.commit()
    }
}