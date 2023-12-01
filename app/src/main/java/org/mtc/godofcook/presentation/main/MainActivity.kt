package org.mtc.godofcook.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.mtc.godofcook.R
import org.mtc.godofcook.databinding.ActivityMainBinding
import org.mtc.godofcook.presentation.add.AddActivity
import org.mtc.godofcook.presentation.detail.DetailActivity
import org.mtc.godofcook.util.activity.hideKeyboard
import org.mtc.godofcook.util.binding.BindingActivity
import org.mtc.godofcook.util.fragment.AlertDialogFragment

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFoodListEqualCategory("", "", "")
        mainAdapter = MainAdapter(
            { food -> showFoodDeleteAlertDialogFragment(food.id) },
            { food -> navigateToDetail(food.id) },
            context = this
        )
        binding.rvMainPost.adapter = mainAdapter

        initSpinnerAdapter()
        initHideKeyBoard()
        initAddBtnClickListener()
        initTextChangedListener()
        initCategorySpinnerSelectedListener()
        initSubCategorySpinnerSelectedListener()
        initCollectCommonFoodList()
    }

    private fun initCollectCommonFoodList() {
        viewModel.commonFoodList.flowWithLifecycle(lifecycle).onEach {
            mainAdapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initSubCategorySpinnerSelectedListener() {
        binding.spinnerMainSubcategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    getFoodList()
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    getFoodList()
                }
            }
    }

    private fun initCategorySpinnerSelectedListener() {
        binding.spinnerMainCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    getFoodList()
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    getFoodList()
                }
            }
    }

    private fun initTextChangedListener() {
        binding.etMain.addTextChangedListener {
            getFoodList()
        }
    }

    private fun initAddBtnClickListener() {
        binding.btnMainAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSpinnerAdapter() {
        ArrayAdapter.createFromResource(
            this,
            R.array.main_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerMainCategory.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.main_sub_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerMainSubcategory.adapter = adapter
        }
    }

    private fun getFoodList() {
        val inputText = binding.etMain.text.toString().trim()
        if (!isKoreanCharacter(inputText)) {
            return
        }
        val category = when (binding.spinnerMainCategory.selectedItem.toString()) {
            "모두보기" -> null
            else -> binding.spinnerMainCategory.selectedItem.toString()
        }

        val subCategory = when (binding.spinnerMainSubcategory.selectedItem.toString()) {
            "모두보기" -> null
            else -> binding.spinnerMainSubcategory.selectedItem.toString()
        }
        viewModel.getFoodListEqualCategory(category, subCategory, binding.etMain.text.toString())
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
            })
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun navigateToDetail(id: Int?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun isKoreanCharacter(text: String): Boolean {
        for (char in text) {
            val unicode = char.code
            if (unicode < 0xAC00 || unicode > 0xD7A3) {
                return false
            }
        }
        return true
    }

    private fun initHideKeyBoard() {
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }
}