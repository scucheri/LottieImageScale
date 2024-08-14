package com.elyeproj.annotationexperiment;

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import java.lang.Math.max

/**
 * Created by xiaoxiaoyu on 2024/8/13.
 */
class TestLottieView  : LottieAnimationView {

    private var index: Int = 0

    constructor(context: Context?)  : super(context){
//          init()
      }

     constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
//         init()
     }

     constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
//         init()
     }

    fun init(index : Int){
        this.index = index
        setAnimation(LOADING_LOTTIE_FILE)
        playAnimation()
        repeatMode = LottieDrawable.RESTART
        repeatCount = LottieDrawable.INFINITE
//        setImageDrawable(context.getDrawable(R.drawable.test_image))
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        super.onDraw(canvas)
        val viewWidth = canvas.width
        val viewHeight = canvas.height

        // 假设动画文件的宽高比为 1:1，则放大并居中显示
        val scaleFactor = maxOf(viewWidth, viewHeight) / minOf(viewWidth, viewHeight).toFloat()
        val cutOutX = max(viewHeight - viewWidth, 0).toFloat()
        val cutOutY = max(viewWidth - viewHeight, 0).toFloat()

        //        val scaleFactor = 2f
        // 创建缩放和平移的Matrix
        val matrix = Matrix().apply {
            postScale(scaleFactor, scaleFactor)
            if (index == 1){
                postTranslate(-cutOutX, -cutOutY)
            } else if (index == 2){
                postTranslate(-viewWidth.toFloat()-cutOutX, 0f-cutOutY)
            }else if (index == 3){
                postTranslate(0f -cutOutX, -viewHeight.toFloat()-cutOutY)
            }else if (index == 4){
                postTranslate(-viewWidth.toFloat()-cutOutX, -viewHeight.toFloat()-cutOutY)
            }
        }
        // 应用Matrix到Lottie Animation View
        scaleType = ScaleType.MATRIX
        imageMatrix = matrix

    }


    companion object{
        private const val LOADING_LOTTIE_FILE = "lottie/create_bot_avatar_loading.json"
    }

}
