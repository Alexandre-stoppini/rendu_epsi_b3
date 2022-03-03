package fr.epsi.rendu_epsi_b3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class ModifCompteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modif_compte)
        val editTextName = findViewById<EditText>(R.id.modifName)
        val editTextForname = findViewById<EditText>(R.id.modifForname)
        val editTextMail = findViewById<EditText>(R.id.modifMail)
        val editTextAddress = findViewById<EditText>(R.id.modifAddress)
        val editTextZip = findViewById<EditText>(R.id.modifZip)
        val editTextCity = findViewById<EditText>(R.id.modifCity)
        val buttonModif = findViewById<Button>(R.id.buttonModif)

        editTextName.setText(readSharedPreference("name"))
        editTextForname.setText(readSharedPreference("forname"))
        editTextMail.setText(readSharedPreference("mail"))
        editTextAddress.setText(readSharedPreference("address"))
        editTextZip.setText(readSharedPreference("zipCode"))
        editTextCity.setText(readSharedPreference("city"))

        buttonModif.setOnClickListener(View.OnClickListener {
            modifCurUser("name",editTextName.text.toString())
            modifCurUser("forname",editTextForname.text.toString())
            modifCurUser("mail",editTextMail.text.toString())
            modifCurUser("address",editTextAddress.text.toString())
            modifCurUser("zipCode",editTextZip.text.toString())
            modifCurUser("city",editTextCity.text.toString())
            val newIntent = Intent(application, TabbarActivity::class.java)
            startActivity(newIntent)
        })
        showBtnBack()
    }
    private fun showBtnBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun readSharedPreference(key: String): String {
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val txt = sharedPreferences.getString(key, "Not found")
        return txt.toString()
    }

    fun modifCurUser(key: String, value: String) {
        val sharerPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = sharerPreferences.edit()
        edit.putString(key, value)
        edit.apply()
    }
}