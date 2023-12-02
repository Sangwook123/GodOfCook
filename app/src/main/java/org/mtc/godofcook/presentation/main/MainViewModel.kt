package org.mtc.godofcook.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.domain.repository.FoodRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    private val _commonFoodList = MutableStateFlow<List<Food>>(emptyList())
    val commonFoodList: StateFlow<List<Food>> = _commonFoodList.asStateFlow()

    fun getFoodListEqualCategory(category: String?, subCategory: String?, name: String?){
        viewModelScope.launch(Dispatchers.IO) {
            foodRepository.getCombineList(name, category, subCategory).collect {
                _commonFoodList.value = it
            }
        }
    }

    fun deleteFood(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(id != null)
                foodRepository.deleteFoodById(id)
        }
    }
}