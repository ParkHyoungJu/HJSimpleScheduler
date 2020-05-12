package kr.hj.simple_scheduler.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kr.hj.simple_scheduler.BaseApplication
import kr.hj.simple_scheduler.R

open class BaseActivity:AppCompatActivity() {

    protected var mContext:Context? = null
    protected var mActivity: Activity? = null
    protected var mDoubleBack:Boolean = false
    protected var isShowDoubleBack:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        mActivity = this
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.setCurrentActivity(this)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        BaseApplication.setCurrentActivity(null)

        super.onDestroy()
    }

    override fun onBackPressed() {
        if(mDoubleBack || !isShowDoubleBack) {
            super.onBackPressed()
            return
        }

        mDoubleBack = true
        toast(getString(R.string.hint_exit), false)

        Handler().postDelayed(Runnable {
            mDoubleBack = false
        }, 2000)

    }

    protected fun toast(s:String, isLongLength:Boolean){
        val duration:Int = if(isLongLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(this, s, duration).show()
    }

    protected fun toast(resId:Int, isLongLength:Boolean){
        val duration:Int = if(isLongLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        Toast.makeText(this, resId, duration).show()
    }
}