package com.example.wb_5_1.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wb_5_1.R
import com.example.wb_5_1.utils.Resource
import com.example.wb_5_1.appComponent
import com.example.wb_5_1.databinding.FragmentMainBinding
import com.example.wb_5_1.domain.model.DotaHeroModelDomain
import javax.inject.Inject


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    private var adapter: MainAdapter? = null

    private var loadingPermission = true

    private val vm: MainViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupObservers()
    }

    private fun setupObservers() {
        vm.dotaHeroesList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
            binding?.mainProgressBar?.visibility = View.GONE
        })

        vm.loadingPermission.observe(viewLifecycleOwner, Observer {
            loadingPermission = it
        })

        if (loadingPermission) {

            vm.getDotaHeroes().observe(viewLifecycleOwner, Observer { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data != null) {
                            vm.setDotaHeroesList(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                        binding?.mainProgressBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding?.mainProgressBar?.visibility = View.VISIBLE
                    }
                }
            })

            vm.changeToFalseLoadingPermission()
        }
    }

    private fun navigateToDetails(hero: DotaHeroModelDomain) {

        findNavController().navigate(
            R.id.action_mainFragment_to_detailsFragment,
            getHeroBundle(hero)
        )
    }

    private fun getHeroBundle(hero: DotaHeroModelDomain): Bundle {
        val heroInfoBundle = Bundle()
        heroInfoBundle.putString("name", hero.name)
        heroInfoBundle.putString("base_str", "${hero.baseStr.toInt()} + ${hero.strGain}")
        heroInfoBundle.putString("base_agi", "${hero.baseAgi.toInt()} + ${hero.agiGain}")
        heroInfoBundle.putString("base_int", "${hero.baseInt.toInt()} + ${hero.intGain}")
        heroInfoBundle.putString("attack_type", hero.attackType)
        heroInfoBundle.putString(
            "base_health",
            "${hero.baseHealth.toInt()} + ${hero.baseHealthRegen}"
        )
        heroInfoBundle.putString("base_mana", "${hero.baseMana.toInt()} + ${hero.baseManaRegen}")
        heroInfoBundle.putString("base_armor", "${hero.baseArmor.toInt()}")
        heroInfoBundle.putString("base_mr", "${hero.baseMr.toInt()}%")
        heroInfoBundle.putString(
            "base_damage",
            "${hero.baseAttackMin.toInt()} - ${hero.baseAttackMax.toInt()}"
        )
        heroInfoBundle.putString("attack_range", "${hero.attackRange.toInt()}")
        heroInfoBundle.putString("move_speed", "${hero.moveSpeed.toInt()}")
        heroInfoBundle.putString("legs", "${hero.legs}")
        heroInfoBundle.putString("icon", hero.icon)
        heroInfoBundle.putString("img", hero.img)

        return heroInfoBundle
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter { navigateToDetails(it) }
        binding?.apply {
            mainRecyclerView.adapter = adapter
            mainRecyclerView.layoutManager = LinearLayoutManager(context)
            mainRecyclerView.itemAnimator = null
            val dividerItemDecoration = DividerItemDecoration(
                mainRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
            ContextCompat.getDrawable(context!!, R.drawable.divider_item)?.let {
                dividerItemDecoration.setDrawable(
                    it
                )
            }
            mainRecyclerView.addItemDecoration(
                dividerItemDecoration
            )
        }
    }

    override fun onDestroyView() {
        binding = null
        adapter = null
        super.onDestroyView()
    }

}