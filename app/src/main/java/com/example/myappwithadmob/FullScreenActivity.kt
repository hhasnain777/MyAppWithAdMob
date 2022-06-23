package com.example.myappwithadmob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class FullScreenActivity : AppCompatActivity() {
    private lateinit var adView: AdView
    private var minterstitialAd: InterstitialAd? = null
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    private val TAG = ".MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        adView = findViewById(R.id.bannerAdView)

        loadAd()

        val adRequest = AdRequest.Builder().build()
        // Start loading the ad in the background.
        adView?.loadAd(adRequest)


        val data = intent.getParcelableExtra<DataClass>("data")
        if (data!=null) run {
            val title: TextView = findViewById(R.id.titleFS)
            val image: ImageView = findViewById(R.id.imageFS)
            val description: TextView = findViewById(R.id.descriptionFS)
            title.text = data.Title
            image.setImageResource(data.Image)
            description.text = data.Description
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showInterstitial()
    }
    private fun showInterstitial() {
        if (minterstitialAd != null) {
            minterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    val intent = Intent(this@FullScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                    minterstitialAd = null
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d(TAG, "Ad failed to show.")

                    minterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                }
            }
            minterstitialAd?.show(this)
        } else {
            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, AD_UNIT_ID, adRequest, object: InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(adError: LoadAdError) {
                minterstitialAd = null
                Toast.makeText(this@FullScreenActivity, "onAdFailedToLoad() with error", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                minterstitialAd = interstitialAd
                Toast.makeText(this@FullScreenActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
            }
        })
    }
}