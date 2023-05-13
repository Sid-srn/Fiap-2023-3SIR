package com.example.explorefragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.explorefragments.databinding.FragmentListBinding


class ListFragment : Fragment() {

    lateinit var bind: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FragmentListBinding.bind(view)

        bind.btnA.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //carrega fragment
                loadFragment(DetailFragmentA())
            } else {
                //carrego activity
                loadFragment(DetailFragmentA())
            }
        }

        bind.btnB.setOnClickListener {
            loadFragment(DetailFragmentB())
        }

        bind.btnC.setOnClickListener {
            loadFragment(DetailFragmentC())
        }
    }

    private fun loadFragment(detailFrag: Fragment) {
        val fragManager = parentFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        //fragTransaction.add(R.id.detailsVertical, detailFrag)
        fragTransaction.replace(R.id.detailsVertical, detailFrag)
        fragTransaction.addToBackStack(null)
        fragTransaction.commit()
    }

}