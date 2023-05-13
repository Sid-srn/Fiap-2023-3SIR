package com.example.explorefragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.explorefragments.databinding.FragmentTitlesBinding

class TitlesFragment : Fragment() {

    private lateinit var binding: FragmentTitlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_titles, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTitlesBinding.bind(view)

        binding.btnA.setOnClickListener {
            openDetail(DetailItemAFragment(), "A")
        }

        binding.btnB.setOnClickListener {
            openDetail(DetailItemBFragment(), "B")
        }

        binding.btnC.setOnClickListener {
            openDetail(DetailItemCFragment(), "C")
        }
    }

    private fun openDetail(detailItemFragment: Fragment, fragmentId: String) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            //fragmentTransaction.add(R.id.details, detailItemFragment)
            fragmentTransaction.replace(R.id.details, detailItemFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else {
            val intent = Intent().apply {
                setClass(requireContext(), DetailActivity::class.java)
                putExtra("Frag", fragmentId)
            }
            startActivity(intent)
        }
    }

}