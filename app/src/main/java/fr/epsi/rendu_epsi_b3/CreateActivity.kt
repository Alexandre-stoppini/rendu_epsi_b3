package fr.epsi.rendu_epsi_b3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CreateActivity :  BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val createUser:Button = findViewById(R.id.create_user)
        val get_name = findViewById<EditText>(R.id.get_name)
        val get_forname = findViewById<EditText>(R.id.get_forname)
        val get_mail = findViewById<EditText>(R.id.get_mail)
        val get_address = findViewById<EditText>(R.id.get_address)
        val get_zipCode = findViewById<EditText>(R.id.get_zip)
        val get_city = findViewById<EditText>(R.id.get_city)
        val get_fidelitycard = findViewById<EditText>(R.id.get_card)

        createUser.setOnClickListener(View.OnClickListener{
            writeNewUser("name", get_name.text.toString())
            writeNewUser("forname", get_forname.text.toString())
            writeNewUser("mail", get_mail.text.toString())
            writeNewUser("address", get_address.text.toString())
            writeNewUser("zipCode", get_zipCode.text.toString())
            writeNewUser("city", get_city.text.toString())
            writeNewUser("fidelitycard", get_fidelitycard.text.toString())
            val newIntent = Intent(application, CarteActivity::class.java)
            startActivity(newIntent)
        })
    }

    fun writeNewUser(key : String, value: String){
        val sharerPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit=sharerPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }

}