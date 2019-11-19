package pt.andregdias.freezingpoint

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wine::class], version = 2)
abstract class WineRoomDatabase : RoomDatabase() {
  abstract fun wineDao(): WineDao

  companion object {
    @Volatile
    private var INSTANCE: WineRoomDatabase? = null

    fun getDatabase(context: Context): WineRoomDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          WineRoomDatabase::class.java,
          "Wine_database"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        return instance
      }
    }
  }
}
