package fr.epsi.rendu_epsi_b3

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

open class BaseActivity : AppCompatActivity() {

    fun setHeaderTitle(txt: String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = txt
    }

    override fun onCreate (savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Epsi", "################ on create ##############" + javaClass.simpleName)
    }
}