package kr.hj.simple_scheduler.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class FlowLinearLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var line_height : Int = 0

    companion object class LayoutParams : ViewGroup.LayoutParams{

        var horizontal_spacing : Int
        var vertical_spacing : Int

        constructor(horizontal_spacing : Int, vertical_spacing : Int) : super(0, 0) {
            this.horizontal_spacing = horizontal_spacing
            this.vertical_spacing = vertical_spacing
        }
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        var height = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        val count = childCount
        var line_height = 0

        var xpos = paddingLeft
        var ypos = paddingTop

        var childHeightMeasureSpec : Int
        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST)
        }else{
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        }

        for (i in 0 until count){
            val child = getChildAt(i)
            if(child.visibility != GONE){
                val lp = child.layoutParams as LayoutParams
                child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec)
                val childw = child.measuredWidth
                line_height = Math.max(line_height, child.measuredHeight + lp.vertical_spacing)

                if(xpos + childw > width){
                    xpos = paddingLeft
                    ypos += line_height
                }

                xpos += childw + lp.horizontal_spacing
            }
        }

        this.line_height = line_height

        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED){
            height = ypos + line_height
        }else if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            if(ypos + line_height < height){
                height = ypos + line_height
            }
        }
        setMeasuredDimension(width, height)
    }

    protected override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return LayoutParams(1, 1)
    }

    protected override fun generateLayoutParams(p: ViewGroup.LayoutParams?): ViewGroup.LayoutParams {
        return LayoutParams(1, 1)
    }

    protected override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        p?.let {
            if(p is LayoutParams) return true
        }

        return false
    }

    protected override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        val width = r - l
        var xpos = paddingLeft
        var ypos = paddingTop

        for (i in 0 until count){
            val child = getChildAt(i)
            if(child.visibility != GONE){
                val childw = child.measuredWidth
                val childh = child.measuredHeight
                val lp = child.layoutParams as LayoutParams
                if(xpos + childw > width){
                    xpos = paddingLeft
                    ypos += line_height
                }
                child.layout(xpos, ypos, xpos + childw, ypos + childh)
                xpos += childw + lp.horizontal_spacing
            }
        }
    }
}