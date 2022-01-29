package ru.fefu.activitytracker.activity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.fefu.activitytracker.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.activity.cards.ListItem
import ru.fefu.activitytracker.databinding.*

class ListItemAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder> (ListItemCallback()) {

    private var itemClickListener: (Int) -> Unit = {}

    fun serItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }
    fun getItemClickListener(): (Int) -> Unit {
        return itemClickListener
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is ListItem.DateCard -> R.layout.date_label
            is ListItem.MyCard -> R.layout.personal_activity
            is ListItem.UserCard -> R.layout.user_activity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.date_label -> DateCardViewHolder (
                DateLabelBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                        )
                    )
            R.layout.personal_activity -> MyCardViewHolder (
                PersonalActivityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                    )
            R.layout.user_activity -> UserCardViewHolder (
                UserActivityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                    )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DateCardViewHolder -> holder.bind(currentList[position] as ListItem.DateCard)
            is MyCardViewHolder -> holder.bind(currentList[position] as ListItem.MyCard)
            is UserCardViewHolder -> holder.bind(currentList[position] as ListItem.UserCard)
        }
    }

    inner class DateCardViewHolder(private val binding: DateLabelBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dateCard : ListItem.DateCard) {
            binding.activityDate.text = dateCard.date
        }
    }

    inner class MyCardViewHolder(private val binding: PersonalActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.myCardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(myCard: ListItem.MyCard) {
            binding.activityDistance.text = myCard.distance
            binding.activityDuration.text = myCard.duration
            binding.activityStartTime.text = myCard.start_time
            binding.activityType.text = myCard.type
        }
    }

    inner class UserCardViewHolder(private val binding: UserActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.userCardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) itemClickListener.invoke(position)
            }
        }
        fun bind(userCard: ListItem.UserCard) {
            binding.activityDistance.text = userCard.distance
            binding.activityUsername.text = userCard.username
            binding.activityDuration.text = userCard.duration
            binding.activityStartTime.text = userCard.start_time
            binding.activityType.text = userCard.type
        }
    }

    internal class ListItemCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.DateCard && newItem is ListItem.DateCard ->
                    oldItem.date == newItem.date
                oldItem is ListItem.MyCard && newItem is ListItem.MyCard ->
                    oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.DateCard && newItem is ListItem.DateCard ->
                    areItemsTheSame(oldItem, newItem)
                oldItem is ListItem.MyCard && newItem is ListItem.MyCard ->
                    oldItem == newItem
                else -> false
            }

        }
    }
}
