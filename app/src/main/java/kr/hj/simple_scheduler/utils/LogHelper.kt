package kr.hj.simple_scheduler.utils

import android.util.Log
import kr.hj.simple_scheduler.BuildConfig

class LogHelper {
    companion object{
        private val isLoging:Boolean = BuildConfig.DEBUG
        private val PRE_TAG:String = "HJ_Scheduler::"

        fun getLogTag():String{
            var level = 4
            var str:StringBuffer = StringBuffer()

            var trace:StackTraceElement = Thread.currentThread().stackTrace[level]
            var fullClassName:String = trace.className
            var className:String = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)

            while (className.equals("LogHelper")){
                level++
                trace = Thread.currentThread().stackTrace[level]
                fullClassName = trace.className
                className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
            }

            val methodName:String = trace.methodName
            val lineNumber = trace.lineNumber

            str.append(PRE_TAG + className)
                    .append(".")
                    .append(methodName)
                    .append("():")
                    .append(lineNumber)

            return str.toString()
        }

        fun dStart() = if(!isLoging){} else Log.d(getLogTag(), "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< START LOG")

        fun dEnd() = if(!isLoging){} else Log.d(getLogTag(), "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< END LOG")

        fun d(paramString:String) = if(!isLoging){} else Log.d(getLogTag(), paramString)

        fun e(paramString:String) = if(!isLoging){} else Log.e(getLogTag(), paramString)
    }
}