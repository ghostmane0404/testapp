package com.example.testproject.core.shared.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.R
import com.example.testproject.core.data.network.responses.TopResponse
import com.example.testproject.core.utils.loadImage
import com.example.testproject.databinding.ItemTopGameBinding
import kotlinx.android.synthetic.main.item_top_game.view.*

class TopGamesAdapter() :
    PagingDataAdapter<TopResponse, TopGamesAdapter.TopGamesVH>(
        TOP_GAME_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGamesVH {
        val binding =
            ItemTopGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopGamesVH(binding)
    }

    override fun onBindViewHolder(holder: TopGamesVH, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem!!,position = position)

    }

    class TopGamesVH(private val binding: ItemTopGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: TopResponse?,
            position: Int
        ) {
            binding.apply {
                image.loadImage(item?.game?.box?.large)
                name.text = item?.game?.name
                channels.text = itemView.context.getString(R.string.channels,item?.channels)
                viewers.text = itemView.context.getString(R.string.viewers,item?.viewers)
            }
        }
    }

    companion object {
        private val TOP_GAME_COMPARATOR = object : DiffUtil.ItemCallback<TopResponse>() {
            override fun areItemsTheSame(
                oldItem: TopResponse,
                newItem: TopResponse
            ) = oldItem.game._id == newItem.game._id

            override fun areContentsTheSame(
                oldItem: TopResponse,
                newItem: TopResponse
            ) = oldItem == newItem

        }
    }
}