package fr.epsi.rendu_epsi_b3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

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
        val modif_user = findViewById<Button>(R.id.button_to_modif)

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