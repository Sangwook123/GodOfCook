package org.mtc.godofcook.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.mtc.godofcook.databinding.ItemHomePostBinding
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.util.view.ItemDiffCallback

class MainAdapter(
    private val onLongClicked: (Food) -> Unit,
    private val onClicked: (Food) -> Unit,
    private val context: Context
) : ListAdapter<Food, MainViewHolder>(DiffUtil) {
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemHomePostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onLongClicked,
            onClicked,
            context
        )
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Food>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}