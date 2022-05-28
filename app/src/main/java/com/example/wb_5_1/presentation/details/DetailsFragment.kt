package com.example.wb_5_1.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.wb_5_1.R
import com.example.wb_5_1.databinding.FragmentDetailsBinding
import com.example.wb_5_1.utils.Constants

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        binding?.detailsToolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupUI() {
        binding?.apply {

            detailsToolbar.title = arguments?.getString("name")

            heroNameDetailsTextView.text = arguments?.getString("name")
            strTextView.text = arguments?.getString("base_str")
            agiTextView.text = arguments?.getString("base_agi")
            intTextView.text = arguments?.getString("base_int")
            attackTypeTextView.text = arguments?.getString("attack_type")
            baseHealthTextView.text = arguments?.getString("base_health")
            baseManaTextView.text = arguments?.getString("base_mana")
            baseMrTextView.text = arguments?.getString("base_mr")
            baseArmorTextView.text = arguments?.getString("base_armor")
            damageTextView.text = arguments?.getString("base_damage")
            attackRangeTextView.text = arguments?.getString("attack_range")
            moveSpeedTextView.text = arguments?.getString("move_speed")
            legsTextView.text = arguments?.getString("legs")

            heroIconDetailsImageView.load(Constants.BASE_IMAGE_URL
                    + arguments?.getString("icon")) {
                placeholder(R.drawable.dota_icon)
            }

            heroImageImageView.load(Constants.BASE_IMAGE_URL
                    + arguments?.getString("img"))
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}