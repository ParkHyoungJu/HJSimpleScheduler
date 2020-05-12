package kr.hj.simple_scheduler.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kr.hj.simple_scheduler.R

class MainActivity : BaseActivity() {

    var moveX = 0f
    var moveY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isShowDoubleBack = true

        moveTo(iv_move_item)
    }

    // 아이콘 이동 시키기
    fun moveTo(view : View){
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
