package pt.andregdias.freezingpoint

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WineDao {
  @Query("SELECT * from wine_table ORDER BY id ASC")
  fun getAllWines(): LiveData<List<Wine>>

  @Insert(onConflict = REPLACE)
  fun insert(wine: Wine)

  @Delete
  fun delete(wine: Wine)

  @Query("DELETE FROM wine_table")
  fun deleteAll()
}
