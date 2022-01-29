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
import ru.fefu.activitytracker.map.cards.TypeCardName

class TypeListAdapter (
    private val Cards: List<TypeCard>
) : RecyclerView.Adapter<TypeListAdapter.MapItemViewHolder>() {

    private var items = mutableListOf<View>()

    private var selCheck = -1

    private var itemClickListener: (Int, TypeCardName) -> Unit = {
            _, _ ->
    }
    fun setItemClickListener(listener: (Int, TypeCardName) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TypeListAdapter.MapItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_activity_card, parent, false)
        items.add(view)
        return MapItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeListAdapter.MapItemViewHolder, position: Int) {
        holder.bind(Cards[position])
    }

    override fun getItemCount(): Int = Cards.size

    inner class MapItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMapItemName: TextView = itemView.findViewById(R.id.new_activity_type)
        private lateinit var type: TypeCardName
        init {
            itemView.setOnClickListener {
                items.forEach { views ->
                    views.isSelected = true
                    if (adapterPosition != RecyclerView.NO_POSITION)
                        itemClickListener.invoke(adapterPosition, type)
                }

                Cards[adapterPosition].selected = true
                if(selCheck != -1 && selCheck != adapterPosition) {
                    Cards[selCheck].selected = false
                }
                selCheck = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun bind(item: TypeCard) {
            tvMapItemName.text = TypeCardName.values()[item.type].type
            type = TypeCardName.values()[item.type]
            itemView.isSelected = item.selected

            with(itemView as MaterialCardView) {
                if (itemView.isSelected) {
                    strokeWidth = resources.getDimensionPixelSize(R.dimen.Selected)
                    strokeColor = ResourcesCompat.getColor(resources, R.color.purple_500, null)
                } else {
                    strokeWidth = resources.getDimensionPixelSize(R.dimen.notSelected)
                    strokeColor = ResourcesCompat.getColor(resources, R.color.grey, null)
                }
            }
        }
    }
}