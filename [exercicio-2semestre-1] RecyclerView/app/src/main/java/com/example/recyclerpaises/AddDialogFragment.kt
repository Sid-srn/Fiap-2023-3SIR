package com.example.recyclerpaises

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.recyclerpaises.databinding.AddDialogFragmentBinding

class AddDialogFragment : DialogFragment() {

    lateinit var bind: AddDialogFragmentBinding
    var addListener: IAddPais? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        bind = AddDialogFragmentBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(bind.root)
            .setPositiveButton("Salvar", DialogInterface.OnClickListener { dialog, id ->
                addListener?.addPais(
                    PaisModel(
                        bind.txtPais.text.toString(),
                        bind.txtContinente.text.toString(),
                    )
                )
                dialog.cancel()
            })
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }
        return builder.create()

    }

    companion object {
        fun newInstance(
            listener: IAddPais
        ): AddDialogFragment {
            val args = Bundle()
            val dialog = AddDialogFragment()
            dialog.addListener = listener
            dialog.arguments = args
            return dialog
        }
    }
}