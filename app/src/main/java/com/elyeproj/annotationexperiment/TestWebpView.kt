package com.elyeproj.annotationexperiment;

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedImageDrawable
import android.net.Uri
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo


/**
 * Created by xiaoxiaoyu on 2024/8/13.
 */
class TestWebpView  : SimpleDraweeView {

    private var resourceWidth : Float = 1f
    private var resourceHeight : Float = 1f
    private var index: Int = 0
    private var controllerListener: ControllerListener<ImageInfo> = object : BaseControllerListener<ImageInfo>() {
        override fun onFinalImageSet(
            id: String,
            @Nullable imageInfo: ImageInfo?,
            @Nullable anim: Animatable?,
        ) {
            anim?.start()
//            val width = imageInfo?.width ?: return
//            val height = imageInfo?.height ?: return
//            resourceWidth =  width.toFloat()
//            resourceHeight = height.toFloat()
//            invalidate()
        }
    }

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


        //        setImageDrawable(context.getDrawable(R.drawable.bg_ugc_create_placeholder))
//        setImageDrawable(context.getDrawable(R.drawable.test_image))
//image_sketch_loading_3
//         bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_ugc_create_placeholder)


        val drawable = context.getDrawable(R.drawable.loading)
        drawable?.let { // 确保 Drawable 已设置边界
            drawable.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            resourceWidth = it.bounds.width().toFloat()
            resourceHeight = it.bounds.height().toFloat()
            Log.i("testxiaoyu onDraw resourceWidth ", "$resourceWidth ")
            Log.i("testxiaoyu onDraw resourceHeight ", "$resourceHeight ")
        }
        val build = Fresco.newDraweeControllerBuilder()
            .setUri(Uri.parse("res://" + context.packageName + "/" + R.drawable.bg_ugc_create_placeholder))
            .setControllerListener(controllerListener).build()
        this.controller = build

    }

    override fun onDraw(canvas: Canvas?) {

        canvas ?: return

        val viewWidth = canvas.width
        val viewHeight = canvas.height

        // 创建缩放和平移的Matrix
        val matrix = Matrix().apply {
//            if (index != 1) {
                postScale(
                    2f,
                    2f
                )
//            }
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
//        scaleType = ScaleType.MATRIX
//        imageMatrix = matrix

            canvas.save()
            canvas.concat(matrix)
            drawable.draw(canvas)
            canvas.restore()

//        super.onDraw(canvas)
    }

    fun Number.dp2px(): Int {
        val f = toFloat()
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (f * scale + 0.5f).toInt()
    }


    companion object {
        //        private const val LOADING_LOTTIE_FILE = "lottie/create_bot_avatar_loading.json"
//        private const val LOADING_LOTTIE_FILE = "lottie/ic_create_bot_lottie.json"
//        private const val LOADING_LOTTIE_FILE = "lottie/ai_generate_loading_sparkles.json"
//        private const val LOADING_LOTTIE_FILE = "lottie/realtime_call_speaking.json"
//        private const val LOADING_LOTTIE_FILE = "lottie/test1.json"
//        private const val LOADING_LOTTIE_FILE = "lottie/loading_new.json"

        private const val LOADING_LOTTIE_FILE = "drawable/loading.webp"

    }

}
