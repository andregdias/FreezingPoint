package pt.andregdias.freezingpoint

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WineViewModel(application: Application) : AndroidViewModel(application) {
  private var parentJob = Job()
  private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
  private val scope = CoroutineScope(coroutineContext)

  private val repository: WineRepository
  val allWines: LiveData<List<Wine>>

  init {
    val wineDao = WineRoomDatabase.getDatabase(application).wineDao()
    repository = WineRepository(wineDao)
    allWines = repository.allWines
  }

  fun insert(wine: Wine) = scope.launch(Dispatchers.IO) {
    repository.insert(wine)
  }

  fun delete(wine: Wine) = scope.launch(Dispatchers.IO) {
    repository.delete(wine)
  }

  fun deleteAll() = scope.launch(Dispatchers.IO) {
    repository.deleteAll()
  }

  override fun onCleared() {
    super.onCleared()
    parentJob.cancel()
  }

}
