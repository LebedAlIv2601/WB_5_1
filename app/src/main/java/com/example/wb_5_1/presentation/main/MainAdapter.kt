package com.example.wb_5_1.presentation.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wb_5_1.utils.Constants.BASE_IMAGE_URL
import com.example.wb_5_1.R
import com.example.wb_5_1.databinding.MainRvItemBinding
import com.example.wb_5_1.domain.model.DotaHeroModelDomain

class MainAdapter(private val onClick: (DotaHeroModelDomain) -> Unit) :
    ListAdapter<DotaHeroModelDomain, MainAdapter.MainViewHolder>(DiffCallback()) {

    class MainViewHolder(private val binding: MainRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DotaHeroModelDomain) {
            binding.apply {
                heroNameTextView.text = item.name
                heroAttackTypeTextView.text = item.attackType
                heroPrimaryAttrImageView.setImageResource(
                    when (item.primaryAttr) {
                        "str" -> R.drawable.strength_attribute_symbol
                        "int" -> R.drawable.intelligence_attribute_symbol
                        "agi" -> R.drawable.agility_attribute_symbol
                        else -> R.drawable.strength_attribute_symbol
                    }
                )
                heroIconImageView.load(BASE_IMAGE_URL + item.icon) {
                    placeholder(R.drawable.dota_icon)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.main_rv_item,
                    parent, false
                )
                return MainViewHolder(MainRvItemBinding.bind(view))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val holder = MainViewHolder.from(parent)
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                getItem(holder.adapterPosition)?.let(onClick)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<DotaHeroModelDomain>() {
        override fun areItemsTheSame(
            oldItem: DotaHeroModelDomain,
            newItem: DotaHeroModelDomain
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: DotaHeroModelDomain,
            newItem: DotaHeroModelDomain
        ) = (oldItem.primaryAttr == newItem.primaryAttr) &&
                (oldItem.name == newItem.name) && (oldItem.attackType == newItem.attackType) &&
                (oldItem.icon == newItem.icon)

    }
}