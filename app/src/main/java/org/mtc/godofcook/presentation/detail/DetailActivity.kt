package org.mtc.godofcook.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.mtc.godofcook.R
import org.mtc.godofcook.databinding.ActivityDetailBinding
import org.mtc.godofcook.util.binding.BindingActivity
import org.mtc.godofcook.util.fragment.AlertDialogFragment

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val viewModel: DetailViewModel by viewModels()
    private val id by lazy { intent.getIntExtra("id", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFood(id)
        viewModel.getFood.flowWithLifecycle(lifecycle).onEach {
            binding.data = it
        }.launchIn(lifecycleScope)

        binding.tvDetailDelete.setOnClickListener {
            showFoodDeleteAlertDialogFragment(id)
        }

        viewModel.deleteResult.flowWithLifecycle(lifecycle).onEach {
            if(it) finish()
        }.launchIn(lifecycleScope)
    }

    private fun showFoodDeleteAlertDialogFragment(id: Int?) {
        val dialog = AlertDialogFragment.newInstance(
            "삭제하시겠습니까?",
            "취소",
            "삭제",
            handleNegativeButton = {
            },
            handlePositiveButton = {
                viewModel.deleteFood(id)
                this@DetailActivity.finish()
            })
        dialog.show(supportFragmentManager, "dialog")
    }
}