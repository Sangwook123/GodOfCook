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
    private val _getFoodByCategory = MutableStateFlow<List<Food>>(emptyList())
    val getFoodByCategory : StateFlow<List<Food>> = _getFoodByCategory.asStateFlow()

    private val _getFoodByName = MutableStateFlow<List<Food>>(emptyList())
    val getFoodByName : StateFlow<List<Food>> = _getFoodByCategory.asStateFlow()

    private val _getFoodBySubCategory = MutableStateFlow<List<Food>>(emptyList())
    val getFoodBySubCategory : StateFlow<List<Food>> = _getFoodByCategory.asStateFlow()

    private val _commonFoodList = MutableStateFlow<List<Food>>(emptyList())
    val commonFoodList: StateFlow<List<Food>> = _commonFoodList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                _getFoodByCategory,
                _getFoodByName,
                _getFoodBySubCategory
            ) { categoryList, nameList, subCategoryList ->
                findCommonElements(categoryList, nameList, subCategoryList)
            }.collect {
                Log.e("음식","${it}")
                _commonFoodList.value = it
            }
        }
    }

    private fun findCommonElements(
        list1: List<Food>,
        list2: List<Food>,
        list3: List<Food>
    ): List<Food> {
        val commonElements = mutableListOf<Food>()

        for (food1 in list1) {
            if (food1 in list2 && food1 in list3) {
                commonElements.add(food1)
            }
        }

        return commonElements
    }

    fun getFoodListEqualCategory(category: String?, subCategory: String?, name: String?){
        viewModelScope.launch(Dispatchers.IO) {
            if (category.isNullOrBlank()) {
                foodRepository.getAll().collect {
                    Log.e("카테고리", "${it}")
                    _getFoodByCategory.value = it
                }
            } else {
                foodRepository.getEqualCategory(category).collect {
                    _getFoodByCategory.value = it
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            if(name.isNullOrBlank()){
                foodRepository.getAll().collect{
                    Log.e("이름","${it}")
                    _getFoodByName.value = it
                }
            }else{
                foodRepository.getContainInput(name).collect{
                    _getFoodByName.value = it
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            if(subCategory.isNullOrBlank()){
                foodRepository.getAll().collect{
                    Log.e("서브카테고리","${it}")
                    _getFoodBySubCategory.value = it
                }
            }else{
                foodRepository.getEqualSubCategory(subCategory).collect{
                    _getFoodBySubCategory.value = it
                }
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