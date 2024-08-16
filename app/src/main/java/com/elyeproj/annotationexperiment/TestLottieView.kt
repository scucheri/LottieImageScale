package com.elyeproj.annotationexperiment;

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieListener
import com.airbnb.lottie.LottieTask


/**
 * Created by xiaoxiaoyu on 2024/8/13.
 */
class TestLottieView  : LottieAnimationView {

    private var resourceWidth : Float = 1f
    private var resourceHeight : Float = 1f
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

        // 加载动画文件
        val task = LottieCompositionFactory.fromAsset(context, LOADING_LOTTIE_FILE)
        task.addListener(object : LottieListener<LottieComposition>{
            override fun onResult(result: LottieComposition?) {
                    val result = result ?: return
                    val bounds = result.getBounds()
                    resourceWidth = bounds.width().toFloat()
                    resourceHeight = bounds.height().toFloat()
                   invalidate()
            }
        })


        //        setImageDrawable(context.getDrawable(R.drawable.bg_ugc_create_placeholder))
//        setImageDrawable(context.getDrawable(R.drawable.test_image))
//image_sketch_loading_3
//         bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_ugc_create_placeholder)


//        setImageDrawable(context.getDrawable(R.drawable.test_image))

        drawable?.let { // 确保 Drawable 已设置边界
            drawable.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            resourceWidth =  it.bounds.width().toFloat()
            resourceHeight = it.bounds.height().toFloat()
        }

    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val viewWidth = canvas.width
        val viewHeight = canvas.height

        // 创建缩放和平移的Matrix
        val matrix = Matrix().apply {
            postScale(2* viewWidth.toFloat()/resourceWidth, 2* viewHeight.toFloat()/resourceHeight)
            if (index == 1){
                postTranslate(0f, 0f)
            } else if (index == 2){
                postTranslate(-viewWidth.toFloat(), 0f)
            }else if (index == 3){
                postTranslate(0f , -viewHeight.toFloat())
            }else if (index == 4){
                postTranslate(-viewWidth.toFloat(), -viewHeight.toFloat())
            }
        }
        // 应用Matrix到Lottie Animation View
        scaleType = ScaleType.MATRIX
        imageMatrix = matrix

        super.onDraw(canvas)
    }


    companion object{
        private const val LOADING_LOTTIE_FILE = "lottie/create_bot_avatar_loading.json"
    }

}
