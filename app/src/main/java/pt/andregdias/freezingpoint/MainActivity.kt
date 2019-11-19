package pt.andregdias.freezingpoint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.average
import kotlinx.android.synthetic.main.content_main.freezingRed
import kotlinx.android.synthetic.main.content_main.freezingWhite
import kotlinx.android.synthetic.main.content_main.totalVolume
import kotlinx.android.synthetic.main.content_main.wineRecyclerView
import pt.andregdias.freezingpoint.adapter.WineEntryAdapter
import pt.andregdias.freezingpoint.view.InputDialog
import pt.andregdias.freezingpoint.view.InputDialog.DialogListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), DialogListener {
  private lateinit var wineViewModel: WineViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val adapter = WineEntryAdapter(this, this)
    wineRecyclerView.adapter = adapter
    wineViewModel = ViewModelProviders.of(this).get(WineViewModel::class.java)
    wineViewModel.allWines.observe(this, Observer { wines ->
      wines?.let {
        adapter.setWines(it)
        updateCalculations(wines)
      }
    })
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_new -> {
        InputDialog(this, this).show()
        true
      }
      R.id.action_deleteAll -> {
        wineViewModel.deleteAll()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun addWine(wine: Wine) {
    wineViewModel.insert(wine)
  }

  override fun deleteWine(wine: Wine) {
    wineViewModel.delete(wine)
  }

  private fun updateCalculations(wines: List<Wine>) {
    val decimalFomat = DecimalFormat("#.##")
    val data = FreezingPoints(wines)
    totalVolume.text = data.totalVolume.toString()
    average.text = decimalFomat.format(data.average)
    freezingWhite.text = decimalFomat.format(data.freezingWhite)
    freezingRed.text = decimalFomat.format(data.freezingRed)
  }
}
