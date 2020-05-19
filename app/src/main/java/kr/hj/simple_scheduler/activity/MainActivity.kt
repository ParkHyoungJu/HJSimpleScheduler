package kr.hj.simple_scheduler.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import kr.hj.simple_scheduler.R
import kr.hj.simple_scheduler.utils.SqliteUtils

class MainActivity : BaseActivity() {

    private var icons : MutableList<View> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isShowDoubleBack = true

        icons.add(bg_move_item)
        icons.add(bg_move_item1)
        icons.add(bg_move_item2)
        icons.add(bg_move_item3)


        moveTo(icons)

        ib_refresh.setOnClickListener {
            // 1. 전체 icon 제거
            // 2. 아이콘 다시 그리기

            fl_container.removeAllViews()

            var ib_mt1 = Button(this)
            ib_mt1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher_foreground, 0, 0)
            ib_mt1.setBackgroundResource(R.color.transparent)
            ib_mt1.setText("new 1")
            ib_mt1.transformationMethod = null
            fl_container.addView(ib_mt1)

            var ib_mt2 = Button(this)
            ib_mt2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_launcher_foreground, 0, 0)
            ib_mt2.setBackgroundResource(R.color.transparent)
            ib_mt2.setText("new 2")
            ib_mt2.transformationMethod = null
            fl_container.addView(ib_mt2)

            fl_container.invalidate()

        }

        SqliteUtils.connect(this)
    }

    // 아이콘 이동 시키기
    fun moveTo(views : MutableList<View>){

        for (view in views){
            var moveX = 0f
            var moveY = 0f

            view.setOnTouchListener {
                v, event -> when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v.x - event.rawX
                    moveY = v.y - event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    v.animate()
                            .x(event.rawX + moveX)
                            .y(event.rawY + moveY)
                            .setDuration(0)
                            .start()
                }
            }

                true
            }
        }
    }
}
