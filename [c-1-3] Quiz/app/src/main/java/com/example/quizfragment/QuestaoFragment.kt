package com.example.quizfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizfragment.databinding.FragmentAcertouBinding
import com.example.quizfragment.databinding.FragmentQuestaoBinding
import com.google.android.material.snackbar.Snackbar

class QuestaoFragment : Fragment() {
    private lateinit var binding: FragmentQuestaoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_questao, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestaoBinding.bind(view)

        val question = QuestoesManager().getQuestion()

        binding.txtQuestao.text = question.questao

        question.opcoes.shuffle()

        binding.opt1.text = question.opcoes[0].opccao
        binding.opt2.text = question.opcoes[1].opccao
        binding.opt3.text = question.opcoes[2].opccao
        binding.opt4.text = question.opcoes[3].opccao

        binding.btnConfirmaQuestao.setOnClickListener {

            val selected = binding.optionGroup.checkedRadioButtonId
            val selectedOption = when (selected) {
                binding.opt1.id -> binding.opt1.text
                binding.opt2.id -> binding.opt2.text
                binding.opt3.id -> binding.opt3.text
                binding.opt4.id -> binding.opt4.text
                else -> ""
            }
            if (selectedOption != "") {
                val selectedOpction = question.opcoes.find { question -> selectedOption.equals(question.opccao) }
                if (selectedOpction?.isCorrta == true) {
                    findNavController().navigate(R.id.action_questaoFragment_to_acertouFragment)
                } else {
                    findNavController().navigate(R.id.action_questaoFragment_to_errouFragment)
                }
            }else{
                Snackbar.make(binding.root, "Selecione Uma Opção", Snackbar.LENGTH_SHORT).show()
            }
        }


    }
}