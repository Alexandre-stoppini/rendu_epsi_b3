package fr.epsi.rendu_epsi_b3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class CreateActivityMenu: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_menu)
        val buttonQRCode:Button = findViewById(R.id.buttonQRC)
        val buttonManually:Button = findViewById(R.id.buttonManually)

        buttonManually.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application, CreateActivity::class.java)
            startActivity(newIntent)
        })

    }
}