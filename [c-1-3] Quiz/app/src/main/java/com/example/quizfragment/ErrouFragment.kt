package com.example.quizfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizfragment.databinding.FragmentAcertouBinding
import com.example.quizfragment.databinding.FragmentErrouBinding

class ErrouFragment : Fragment() {
    private lateinit var binding: FragmentErrouBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_errou, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentErrouBinding.bind(view)

        binding.btnTentarNovamente.setOnClickListener {
            findNavController().navigate(R.id.action_errouFragment_to_questaoFragment)
        }
    }
}