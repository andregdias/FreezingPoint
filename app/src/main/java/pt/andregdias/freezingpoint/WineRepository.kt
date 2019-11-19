package pt.andregdias.freezingpoint

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WineRepository(private val wineDao: WineDao) {
  val allWines: LiveData<List<Wine>> = wineDao.getAllWines()

  @WorkerThread
  fun insert(wine: Wine) {
    wineDao.insert(wine)
  }

  @WorkerThread
  fun delete(wine: Wine) {
    wineDao.delete(wine)
  }

  @WorkerThread
  fun deleteAll() {
    wineDao.deleteAll()
  }
}
