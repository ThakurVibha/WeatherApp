package com.example.androidassignemnt2.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.androidassignemnt2.data.Clouds
import com.example.androidassignemnt2.data.Coord
import com.example.androidassignemnt2.room.dao.WeatherReportDao
import com.example.androidassignemnt2.room.databaseutils.DatabaseTypeConvertors
import com.example.androidassignemnt2.room.entity.WeatherReportEntity

@Database(entities = [WeatherReportEntity::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConvertors::class)
abstract class WeatherReportDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherReportDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherReportDatabase? = null

        fun getDatabase(context: Context): WeatherReportDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        WeatherReportDatabase::class.java,
                        "weatherReportDatabase"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }


}