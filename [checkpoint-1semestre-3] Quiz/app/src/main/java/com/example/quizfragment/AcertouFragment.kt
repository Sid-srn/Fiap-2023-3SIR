package com.example.quizfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizfragment.databinding.FragmentAcertouBinding

class AcertouFragment : Fragment() {
    private lateinit var binding: FragmentAcertouBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_acertou, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAcertouBinding.bind(view)
        binding.btnProximaPergunta.setOnClickListener {
            findNavController().navigate(R.id.action_acertouFragment_to_questaoFragment)
        }
    }
}