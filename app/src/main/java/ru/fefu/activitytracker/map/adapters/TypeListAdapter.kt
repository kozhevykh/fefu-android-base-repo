package ru.fefu.activitytracker.map.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.map.cards.TypeCard

class TypeListAdapter (cards: List<TypeCard>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val Cards = cards.toMutableList()
    private var cardClickListener: (Int) -> Unit = {}
    private var selCheck = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_activity_card, parent, false)
        return TypeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TypeListViewHolder).bind(Cards[position])
    }

    override fun getItemCount(): Int = Cards.size

    inner class TypeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val type: TextView = itemView.findViewById(R.id.new_activity_type)

        fun bind(typeCard: TypeCard) {
            type.text = typeCard.type
            itemView.isSelected = typeCard.isSelected

            with(itemView as MaterialCardView) {
                if (itemView.isSelected) {
                    strokeWidth = resources.getDimensionPixelSize(R.dimen.Selected)
                    strokeColor = ResourcesCompat.getColor(resources, R.color.purple_500, null)
                } else {
                    strokeWidth = resources.getDimensionPixelSize(R.dimen.notSelected)
                    strokeColor = ResourcesCompat.getColor(resources, R.color.grey, null)
                }
            }
            itemView.setOnClickListener {
                Cards[adapterPosition].isSelected = true
                if(selCheck != -1 && selCheck != adapterPosition) {
                    Cards[selCheck].isSelected = false
                }
                selCheck = adapterPosition
                notifyDataSetChanged()
            }
        }

    }
} 