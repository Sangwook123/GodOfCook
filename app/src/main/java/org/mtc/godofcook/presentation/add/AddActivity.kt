package org.mtc.godofcook.presentation.add

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.mtc.godofcook.R
import org.mtc.godofcook.databinding.ActivityAddBinding
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.util.binding.BindingActivity
import org.mtc.godofcook.util.view.UiState

@AndroidEntryPoint
class AddActivity : BindingActivity<ActivityAddBinding>(R.layout.activity_add) {
    private val viewModel: AddViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSpinnerAdapter()
        initAddBtnClickListener()
        initAddFoodStateObserver()
    }

    private fun initAddFoodStateObserver() {
        viewModel.addFoodState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    finish()
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun initAddBtnClickListener() {
        binding.btnAdd.setOnClickListener {
            val food = Food(
                id = null,
                binding.etAddName.text.toString(),
                binding.spinnerAddCategory.selectedItem.toString(),
                binding.spinnerAddSubCategory.selectedItem.toString(),
                binding.etAddRecipe.text.toString()
            )
            viewModel.addFood(food)
        }
    }

    private fun initSpinnerAdapter() {
        ArrayAdapter.createFromResource(
            this,
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerAddCategory.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.sub_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerAddSubCategory.adapter = adapter
        }
    }
}