package es.juanTejada.TipCalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.juanTejada.TipCalculator.model.TipCalculator
import es.juanTejada.TipCalculator.model.TipDao

@Database(
    entities = [TipCalculator::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val tipDao: TipDao
    companion object {


        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        ).addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {

                            }
                        })
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }
}
