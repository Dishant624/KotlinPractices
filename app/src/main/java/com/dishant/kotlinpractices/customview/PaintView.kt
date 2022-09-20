package com.dishant.kotlinpractices.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import kotlinx.android.synthetic.main.activity_thread_example.view.*
import kotlin.math.abs

class PaintView(context: Context, @Nullable attrs: AttributeSet) : View(context, attrs) {

    private var btmBackground: Bitmap? = null
    private var btmView: Bitmap? = null
    private var mPaint = Paint()
    private var mPath = Path()
    private var colorBackground: Int = 0
    private var sizeBrush: Int = 0
    private var sizeEraser: Int = 0
    private var mX: Float = 0f
    private var mY: Float = 0f
    private var canvas: Canvas? = null
    private final val DEFFERENCE_SPACE = 4
    private val listOfAction = mutableListOf<Bitmap>()

    init {
        sizeBrush = 12
        sizeEraser = 12
        colorBackground = Color.WHITE;

        mPaint.color = Color.BLACK
        //AntiAliasing smooths out the edges of what is being drawn,
        // but is has no impact on the interior of the shape.
        // See setDither() and setFilterBitmap() to affect how colors are treated
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeWidth = toPx(sizeBrush)

    }

    private fun toPx(sizeBrush: Int): Float {
        return sizeBrush.times(resources.displayMetrics.density)
    }

    //This is called during layout when the size of this view has changed.
    // If you were just added to the view hierarchy, you're called with the old values of 0.
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        btmBackground = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        btmView = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(btmView!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(colorBackground)
        canvas?.drawBitmap(btmBackground!!, 0f, 0f, null)
    }

    fun setColorBackground( color : Int){
        colorBackground = color
        invalidate()
    }

    fun setBrushSize(size : Int){
        sizeBrush = size
        mPaint.strokeWidth = toPx(sizeBrush)
    }

    fun setBrushColor(color : Int){
        mPaint.color = color
    }

    fun setEraserSize(size: Int){
        sizeEraser = size
        mPaint.strokeWidth= toPx(sizeEraser)
    }

    fun enableEraser(){
        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    fun disableEraser(){
        mPaint.xfermode = null
        mPaint.shader = null
        mPaint.maskFilter = null
    }

    fun addLastAction(bitmap: Bitmap){
        listOfAction.add(bitmap)
    }

    fun returnLastAction() {
        if(listOfAction.size >0){
            listOfAction.removeLast()
            if(listOfAction.size >0){
                btmView = listOfAction[listOfAction.size -1]
            }else{
                btmView = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            }

            canvas = Canvas(btmView!!)

            invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event==null) return false

        val x = event.x
        val y = event.y

        when(event.action){

            MotionEvent.ACTION_DOWN ->{
                touchStart(x,y)
            }

            MotionEvent.ACTION_MOVE ->{
                touchMove(x,y)
            }

            MotionEvent.ACTION_UP ->{
                touchUp();
            }

        }

        return super.onTouchEvent(event)
    }

    private fun touchUp() {
        mPath.reset()
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(x - mY)

        if(dx >= DEFFERENCE_SPACE || dy >= DEFFERENCE_SPACE){
            //Add a quadratic bezier from the last point, approaching control point (x1,y1), and ending at (x2,y2).
            // If no moveTo() call has been made for this contour, the first point is automatically set to (0,0).
            mPath.quadTo(x,y,(x+mX)/2,(x+mY)/2)

            mY = y
            mX = x

            canvas?.drawPath(mPath,mPaint)
            invalidate()
        }
    }

    private fun touchStart(x: Float, y: Float) {
        mPath.moveTo(x,y)
        mX = x
        mY = y
    }

    fun getBitmap() : Bitmap{
        isDrawingCacheEnabled = true

        buildDrawingCache()

        val bitmap = Bitmap.createBitmap(drawingCache)

        isDrawingCacheEnabled = false

        return bitmap;
    }

}