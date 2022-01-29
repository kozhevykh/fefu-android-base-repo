package ru.fefu.activitytracker.activity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.cards.DateCard
import ru.fefu.activitytracker.activity.cards.ICard
import ru.fefu.activitytracker.activity.cards.UserCard

class UserListAdapter (cards: List<ICard>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val Cards = cards.toMutableList()
    private var cardClickListener: (Int) -> Unit = {}

    companion object {
        private const val ITEM_TYPE_ACTIVITY = 1
        private const val ITEM_TYPE_DATE = 2
    }

    fun setCardClickListener(listener: (Int) -> Unit) {
        cardClickListener = listener
    }

    override fun getItemCount(): Int = Cards.size

    override fun getItemViewType(position: Int): Int =
        if (Cards[position]::class == UserCard::class) {
            ITEM_TYPE_ACTIVITY
        } else {
            ITEM_TYPE_DATE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_ACTIVITY) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.user_activity, parent, false)
            UsersListViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.date_label, parent, false)
            DateListViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_ACTIVITY) {
            (holder as UsersListViewHolder).bind(Cards[position] as UserCard)
        } else {
            (holder as DateListViewHolder).bind(Cards[position] as DateCard)
        }
    }

    inner class DateListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.activity_date)

        fun bind(dateCard: DateCard) {
            date.text = dateCard.date
        }
    }

    inner class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val distance: TextView = itemView.findViewById(R.id.activity_distance)
        private val username: TextView = itemView.findViewById(R.id.activity_username)
        private val duration: TextView = itemView.findViewById(R.id.activity_duration)
        private val type: TextView = itemView.findViewById(R.id.activity_type)
        private val startTime: TextView = itemView.findViewById(R.id.activity_start_time)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    cardClickListener.invoke(position)
            }
        }

        fun bind(userCard: UserCard) {
            distance.text = userCard.distance
            username.text = userCard.username
            duration.text = userCard.duration
            type.text = userCard.type
            startTime.text = userCard.start_time
        }
    }
}