package kr.hj.simple_scheduler

import android.app.Activity
import android.app.Application
import android.content.Context
import kr.hj.simple_scheduler.utils.LogHelper

class BaseApplication : Application() {

    companion object{
        private var mInstance: BaseApplication? = null
        private var mContext: Context? = null
        private var activity:Activity? = null

        fun getInstance() = mInstance

        fun setCurrentActivity(activity:Activity?) = with(activity){
            BaseApplication.activity = this
        }

        fun getCurrentActivity():Activity? = BaseApplication.activity

    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = applicationContext
        LogHelper.dStart()
    }


}