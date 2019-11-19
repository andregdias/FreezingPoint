package pt.andregdias.freezingpoint.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import pt.andregdias.freezingpoint.R
import pt.andregdias.freezingpoint.Wine
import pt.andregdias.freezingpoint.view.InputDialog
import pt.andregdias.freezingpoint.view.InputDialog.DialogListener

class WineEntryAdapter(
  private val activity: Activity,
  private val dialogListener: DialogListener
) :
  RecyclerView.Adapter<WineEntryAdapter.WineViewHolder>() {

  private var wineList = emptyList<Wine>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
    val itemView =
      LayoutInflater.from(parent.context).inflate(R.layout.wine_layout, parent, false)
    return WineViewHolder(itemView)
  }

  override fun getItemCount() = wineList.size

  override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
    holder.wineAlcohol.text = wineList[position].alcohol.toString()
    holder.wineVolume.text = wineList[position].volume.toString()
    holder.wineName.text = wineList[position].name
    holder.wineVat.text = wineList[position].vat

    holder.wineCard.setOnClickListener {
      InputDialog(
        activity,
        dialogListener,
        wine = wineList[position]
      ).show()
    }
  }

  internal fun setWines(wines: List<Wine>) {
    this.wineList = wines
    notifyDataSetChanged()
  }

  inner class WineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    internal val wineCard: CardView = view.findViewById(R.id.wineCard)
    internal val wineAlcohol: TextView = view.findViewById(R.id.wineAlcohol)
    internal val wineVolume: TextView = view.findViewById(R.id.wineVolume)
    internal val wineName: TextView = view.findViewById(R.id.wineName)
    internal val wineVat: TextView = view.findViewById(R.id.wineVat)
  }
}
