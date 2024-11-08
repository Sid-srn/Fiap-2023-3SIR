package com.example.naviagtionfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.naviagtionfragment.databinding.FragmentContentBinding
import com.example.naviagtionfragment.databinding.FragmentHomeBinding


class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContentBinding.bind(view)

        binding.btnGoToDetail.setOnClickListener {
            findNavController().navigate(R.id.action_contentFragment_to_detailFragment)
        }
    }

}