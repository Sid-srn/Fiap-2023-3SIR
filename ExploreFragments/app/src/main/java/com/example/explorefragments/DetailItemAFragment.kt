package com.example.explorefragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.explorefragments.databinding.FragmentDetailItemABinding
import com.example.explorefragments.databinding.FragmentTitlesBinding

class DetailItemAFragment : Fragment() {

    private lateinit var binding: FragmentDetailItemABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail_item_a, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailItemABinding.bind(view)
    }

}