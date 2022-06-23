package com.example.myappwithadmob

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private lateinit var btnstart: Button
    private lateinit var btnshare: Button
    private lateinit var btnrate: Button
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("ABCDEF012345"))
                .build())



        adView = findViewById<AdView>(R.id.bannerAdView)

        btnstart = findViewById(R.id.btnStart)
        btnshare = findViewById(R.id.btnShare)
        btnrate = findViewById(R.id.btnRate)
        val adRequest = AdRequest.Builder().build()
        // Start loading the ad in the background.
        adView?.loadAd(adRequest)


        btnstart.setOnClickListener {

                    val intent = Intent(this,UnlockActivity::class.java)
                    startActivity(intent)

        }
        btnshare.setOnClickListener {
            val message: String = "I love this App"

                val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share to: "))

        }
        btnrate.setOnClickListener {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=")

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)

        }

    }
}