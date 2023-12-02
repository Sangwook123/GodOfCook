package org.mtc.godofcook.presentation.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import org.mtc.godofcook.databinding.ItemHomePostBinding
import org.mtc.godofcook.domain.entity.Food

class MainViewHolder(
    private val binding: ItemHomePostBinding,
    private val onLongClicked: (Food) -> Unit,
    private val onClicked: (Food) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Food?) {
        binding.data = data
        if (data == null) return

        binding.root.setOnLongClickListener {
            onLongClicked(data)
            return@setOnLongClickListener true
        }
        binding.root.setOnClickListener {
            onClicked(data)
        }

        binding.executePendingBindings()
    }
}