package kr.hj.simple_scheduler.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.AttributeSet
import java.lang.reflect.Constructor

class SqliteUtils{

    companion object {
        private val DATABASE_NAME = "hjScheduler"

        private val DATABASE_VERSION = 1

        /*
            TABLES
         */

        /*
            schedule_master Table
            >>>>>> START <<<<<<
        */

        private val TABLE_SCHEDULE_MASTER = "schedule_master"

        // (PK) int(11) schedule_pid
        private val SCHEDULE_MASTER_COLUMN_SCHEDULE_PID = "schedule_pid"

        // varchar(100) name
        private val SCHEDULE_MASTER_COLUMN_NAME = "name"

        // varchar(10) point_x
        private val SCHEDULE_MASTER_COLUMN_POINT_X = "point_x"

        // varchar(10) point_y
        private val SCHEDULE_MASTER_COLUMN_POINT_Y = "point_y"

        // varchar(100) icon_img
        private val SCHEDULE_MASTER_COLUMN_ICON_IMAGE = "icon_img"

        // varchar(1) status  , 1: 사용 9: 삭제
        private val SCHEDULE_MASTER_COLUMN_STATUS = "status"

        // TIMESTAMP reg_tstamp
        private val SCHEDULE_MASTER_COLUMN_REG_TSTAMP = "reg_tstamp"

        // TIMESTAMP mod_tstamp
        private val SCHEDULE_MASTER_COLUMN_MOD_TSTAMP = "mod_tstamp"

        private val CREATE_TABLE_SCHEDULE_MASTER = "CREATE TABLE $TABLE_SCHEDULE_MASTER" +
                " ($SCHEDULE_MASTER_COLUMN_SCHEDULE_PID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " $SCHEDULE_MASTER_COLUMN_NAME VARCHAR(100) NOT NULL, " +
                " $SCHEDULE_MASTER_COLUMN_POINT_X VARCHAR(10), " +
                " $SCHEDULE_MASTER_COLUMN_POINT_Y VARCHAR(10), " +
                " $SCHEDULE_MASTER_COLUMN_ICON_IMAGE VARCHAR(100), " +
                " $SCHEDULE_MASTER_COLUMN_STATUS VARCHAR(1), " +
                " $SCHEDULE_MASTER_COLUMN_REG_TSTAMP TIMESTAMP, " +
                " $SCHEDULE_MASTER_COLUMN_MOD_TSTAMP TIMESTAMP)"

        /*
            schedule_master Table
            <<<<<< END >>>>>>
        */


        /*
            schedule_detail Table
            >>>>>> START <<<<<<
        */

        // schedule_detail Table
        private val TABLE_SCHEDULE_DETAIL = "schedule_detail"

        /*
            schedule_detail Table
            <<<<<< END >>>>>>
        */

        private var context:Context? = null
        private var dbHelper:SQLiteOpenHelper? = null
        private var connection:SQLiteDatabase? = null
        private var instance:SqliteUtils? = null

        fun connect(context: Context) {
            this.context = context
            this.dbHelper = DataBaseHelper(this.context!!)
            this.connection = this.dbHelper?.writableDatabase
        }

        @Synchronized
        fun getInstance(): SqliteUtils? = run {
            if(this.instance == null) this.instance = SqliteUtils()

            return this.instance
        }

        private class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

            override fun onCreate(db: SQLiteDatabase?) {
                db?.execSQL(CREATE_TABLE_SCHEDULE_MASTER)
            }

            override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                // 버전 변경 체크후 처리
            }
        }
    }
}