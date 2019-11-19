package pt.andregdias.freezingpoint.view

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import pt.andregdias.freezingpoint.R
import pt.andregdias.freezingpoint.Wine

class InputDialog(
  activity: Activity,
  dialogListener: DialogListener? = null,
  var wine: Wine? = null
) :
  AlertDialog.Builder(activity) {

  private val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)

  init {
    val dialogView: View = activity.layoutInflater.inflate(R.layout.alert_input_dialog, null)
    dialogView.setPadding(0, 20, 0, 0)

    val name: EditText = dialogView.findViewById(R.id.nameField)
    val vat: EditText = dialogView.findViewById(R.id.vatField)
    val alcohol: EditText = dialogView.findViewById(R.id.alcoholField)
    val volume: EditText = dialogView.findViewById(R.id.volumeField)

    wine?.let {
      name.setText(it.name)
      vat.setText(it.vat)
      alcohol.setText(it.alcohol.toString())
      volume.setText(it.volume.toString())

      dialogBuilder.setNegativeButton(activity.getString(R.string.delete)) { _, _ ->
        dialogListener?.deleteWine(it)
      }
    }

    dialogBuilder.setView(dialogView)
    dialogBuilder.setPositiveButton(
      activity.getString(R.string.ok)
    ) { _, _ ->
      dialogListener?.let {
        if (!alcohol.text.isEmpty() && !volume.text.isEmpty()) {

          wine?.let { wine ->
            wine.alcohol = alcohol.text.toString().toDouble()
            wine.volume = volume.text.toString().toInt()
            wine.name = name.text.toString()
            wine.vat = vat.text.toString()
            it.addWine(wine)
          } ?: it.addWine(
            Wine(
              alcohol = alcohol.text.toString().toDouble(),
              volume = volume.text.toString().toInt(),
              name = name.text.toString(),
              vat = vat.text.toString()
            )
          )
        }
      }
    }
  }

  override fun show(): AlertDialog {
    val alertDialog = dialogBuilder.create()
    alertDialog.window?.setSoftInputMode(
      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
    )
    alertDialog.show()
    return alertDialog
  }

  interface DialogListener {
    fun deleteWine(wine: Wine)
    fun addWine(wine: Wine)
  }
}
