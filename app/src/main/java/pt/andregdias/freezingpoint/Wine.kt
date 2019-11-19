package pt.andregdias.freezingpoint

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wine_table")
data class Wine(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
  var alcohol: Double,
  var volume: Int,
  var name: String? = null,
  var vat: String? = null
)
