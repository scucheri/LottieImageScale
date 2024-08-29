package com.elyeproj.annotationexperiment;

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.elyeproj.annotationexperiment.TestLottieView.Companion
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by xiaoxiaoyu on 2024/8/13.
 */
class TestLottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.test_lottie)
    }

    override fun onResume() {
        Log.i("testxiaoyu ", "1 shr 1 ${1 shr  1}")
        Log.i("testxiaoyu ", "3 shr 1 ${3 shr 1}")

        super.onResume()
        findViewById<TestWebpView>(R.id.test_lottie_view_1).init(1)
        findViewById<TestWebpView>(R.id.test_lottie_view_2).init(2)
        findViewById<TestWebpView>(R.id.test_lottie_view_3).init(3)
        findViewById<TestWebpView>(R.id.test_lottie_view_4).init(4)

        findViewById<LottieAnimationView>(R.id.star_icon_1).apply {
            setAnimation(LOADING_LOTTIE_FILE)
            playAnimation()
            repeatMode = LottieDrawable.RESTART
            repeatCount = LottieDrawable.INFINITE
        }
        findViewById<LottieAnimationView>(R.id.star_icon_2).apply {
            setAnimation(LOADING_LOTTIE_FILE)
            playAnimation()
            repeatMode = LottieDrawable.RESTART
            repeatCount = LottieDrawable.INFINITE
        }
    }

    companion object{
        private const val LOADING_LOTTIE_FILE = "lottie/ai_generate_loading_sparkles.json"
    }
}
