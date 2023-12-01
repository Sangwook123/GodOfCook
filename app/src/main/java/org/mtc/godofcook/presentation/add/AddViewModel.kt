package org.mtc.godofcook.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.domain.repository.FoodRepository
import org.mtc.godofcook.util.view.UiState
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {
    private val _addFoodState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val addFoodState : StateFlow<UiState<Boolean>> = _addFoodState.asStateFlow()

    fun addFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { foodRepository.addFood(food) }.onSuccess {
                _addFoodState.value = UiState.Success(true)
            }
        }
    }
}