package com.dishant.kotlinpractices.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.dishant.kotlinpractices.R

// website link : https://proandroiddev.com/android-custom-view-level-1-67ed1c3febe1

class CircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var paint: Paint = Paint()
    var isCenter = false
    var centerOfX = 0f
    var centerOfY = 0f
    var strokeWidth = 40f
    private var radiusOfCircleView = 0F

    init {

        val attributeArray: TypedArray? =
            context.theme.obtainStyledAttributes(attrs, R.styleable.circleView, 0, 0)

        val color = attributeArray?.getColor(R.styleable.circleView_circle_background,
        ContextCompat.getColor(context,android.R.color.darker_gray)) ?: android.R.color.darker_gray
        paint.color = color

        isCenter = attributeArray?.getBoolean(R.styleable.circleView_onCenter , false) ?: false

        radiusOfCircleView = attributeArray?.getDimension(R.styleable.circleView_circle_radius,0f) ?: 0f

        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        paint.isDither = true
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.BEVEL
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        if(isCenter){
            centerOfX = (width/2).toFloat()
            centerOfY = (height/2).toFloat()
        }else{
            centerOfX = radiusOfCircleView + strokeWidth
            centerOfY = radiusOfCircleView + strokeWidth
        }

        if(radiusOfCircleView == 0f){
            radiusOfCircleView = if(width > height){
                (height/2).toFloat() - strokeWidth
            }else{
                (width/2).toFloat() - strokeWidth
            }
        }

        canvas?.drawCircle(centerOfX, centerOfY, radiusOfCircleView, paint)
        canvas?.drawLine(0f + 50,0f+50, width.toFloat() - 50, height.toFloat() - 50 , paint)
        canvas?.drawLine(0f + 50 ,height.toFloat()-50, width.toFloat() -50 , 0f + 50 , paint)
        super.onDraw(canvas)
    }
}