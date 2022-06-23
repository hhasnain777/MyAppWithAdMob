package com.example.myappwithadmob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.concurrent.thread

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private var minterstitialAd: InterstitialAd? = null
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    private val TAG = ".MainActivity"
    private lateinit var lottie: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)



        MobileAds.initialize(this) {}
        loadAd()

        image = findViewById(R.id.myImage)
        lottie = findViewById(R.id.myAnimation)
        Handler(Looper.getMainLooper()).postDelayed({
                    lottie.playAnimation()
        },2000)


        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            if (minterstitialAd !=null) {
                showInterstitial()
            }
            else {
                showInterstitial()
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 5000) // 3000 is the delayed time in milliseconds.



    }


    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, AD_UNIT_ID, adRequest, object: InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(adError: LoadAdError) {
                minterstitialAd = null
                Toast.makeText(this@SplashScreenActivity, "onAdFailedToLoad() with error", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                minterstitialAd = interstitialAd
                Toast.makeText(this@SplashScreenActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun showInterstitial() {
        if (minterstitialAd != null) {
            minterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
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
}