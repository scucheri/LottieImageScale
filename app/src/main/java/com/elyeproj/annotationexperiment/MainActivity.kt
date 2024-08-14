package com.elyeproj.annotationexperiment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.elyeproj.annotation.CheckCamelSource
import com.elyeproj.annotation.GenerateSource

@CheckCamelSource
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "AnnotateTest"
    }

    @ReflectRuntime(5)
    val reflectTest: Int = 0

    @GenerateSource(10)
    var generateTest: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main)
        findViewById<View>(R.id.test_lottie).setOnClickListener {
            startActivity(Intent(this, TestLottieActivity::class.java))
        }
        Log.d(TAG, "Before $reflectTest $generateTest")
        bindReflectionValue(this)
        Log.d(TAG, "After $reflectTest $generateTest")
        bindGenerationValue(this)
        Log.d(TAG, "Finally $reflectTest $generateTest")
    }

    private fun bindReflectionValue(target: Any) {
        val declaredFields = target::class.java.declaredFields

        for (field in declaredFields) {
            for (annotation in field.annotations) {
                when(annotation) {
                    is ReflectRuntime -> {
                        field.isAccessible = true
                        field.set(target, annotation.value)
                    }
                }
            }
        }
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ReflectRuntime(val value: Int)
