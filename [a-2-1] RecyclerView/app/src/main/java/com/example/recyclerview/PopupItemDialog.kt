package com.example.recyclerview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.recyclerview.databinding.PopUpItemDialogBinding

class PopupItemDialog : DialogFragment() {

    lateinit var bind: PopUpItemDialogBinding
    var iDialogInterface: IDialogItem? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        bind = PopUpItemDialogBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(bind.root)
            .setPositiveButton("Confirma") { dialog, _ ->
                iDialogInterface?.addItem(
                    ItemModel(
                        bind.txtTitle.text.toString(),
                        bind.txtDescription.text.toString(),
                        false
                    )
                )
                dialog.cancel()
            }
            .setNegativeButton("Cancelar"){ dialog, _ ->
                dialog.cancel()
            }


        return builder.create()

    }

    companion object {
        fun buildDialog(iDialog: IDialogItem): PopupItemDialog {
            val dialog = PopupItemDialog()
            val args = Bundle()
            dialog.iDialogInterface = iDialog
            dialog.arguments = args
            return dialog
        }
    }

}