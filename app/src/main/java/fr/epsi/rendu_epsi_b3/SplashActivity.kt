package fr.epsi.rendu_epsi_b3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val account_created: Boolean = checkIfUserExist("name") != "no_user"

        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            if (!account_created) {
                val newIntent = Intent(application, CreateActivityMenu::class.java)
                startActivity(newIntent)
                finish()
            } else {
                val newIntent = Intent(application, CarteActivity::class.java)
                startActivity(newIntent)
                finish()
            }

        }, 2000)
    }

    fun checkIfUserExist(key: String): String {
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val txt = sharedPreferences.getString(key, "no_user")
        return txt.toString()
    }
}