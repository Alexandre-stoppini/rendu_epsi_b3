package fr.epsi.rendu_epsi_b3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val newIntent=Intent(application,MainActivity::class.java)
            startActivity(newIntent)
            finish()
        },2000)
    }
}